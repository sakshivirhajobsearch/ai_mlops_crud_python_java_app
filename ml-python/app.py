from flask import Flask, request, jsonify
import pickle
import pandas as pd
import os

app = Flask(__name__)

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
MODEL_FILE = os.path.join(BASE_DIR, "linear_regression_model.pkl")

# Load model once
with open(MODEL_FILE, "rb") as f:
    model = pickle.load(f)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json  # Expect JSON {"feature1": [..], "feature2": [..]}
    df = pd.DataFrame(data)
    predictions = model.predict(df)
    return jsonify(predictions.tolist())

if __name__ == '__main__':
    app.run(port=5000)
