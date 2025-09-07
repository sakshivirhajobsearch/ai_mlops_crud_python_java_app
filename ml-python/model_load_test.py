import pickle
import os
import pandas as pd

# Path to the model file
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
MODEL_FILE = os.path.join(BASE_DIR, "linear_regression_model.pkl")

# Load the model
with open(MODEL_FILE, "rb") as f:
    model = pickle.load(f)

print("Model loaded successfully!")

# Inspect model parameters
print("Coefficients:", model.coef_)
print("Intercept:", model.intercept_)

# Example: make a prediction
X_new = pd.DataFrame({
    'feature1': [5.0, 6.0],
    'feature2': [3.4, 3.1]
})

predictions = model.predict(X_new)
print("Predictions:", predictions)
