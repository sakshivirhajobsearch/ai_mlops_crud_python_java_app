import pickle
import pandas as pd
from sklearn.linear_model import LinearRegression
from pathlib import Path

# Paths
MODEL_PATH = Path("linear_regression_model.pkl")
DATA_PATH = Path("data/training_data.csv")

def train_model():
    """
    Train a Linear Regression model using CSV data and save it as a pickle file.
    """
    # Load CSV
    data = pd.read_csv(DATA_PATH)

    # Validate required columns
    required_cols = ['feature1', 'feature2', 'target']
    if not all(col in data.columns for col in required_cols):
        raise ValueError(f"CSV must contain columns: {required_cols}")

    # Features and target
    X = data[['feature1', 'feature2']]
    y = data['target']

    # Train Linear Regression
    model = LinearRegression()
    model.fit(X, y)

    # Save model
    with open(MODEL_PATH, 'wb') as f:
        pickle.dump(model, f)

    print("✅ Model trained and saved to linear_regression_model.pkl")

def predict(input_data):
    """
    Predict a value given a list of features [feature1, feature2].
    """
    # Load model
    with open(MODEL_PATH, 'rb') as f:
        model = pickle.load(f)

    # Predict
    return model.predict([input_data])[0]

if __name__ == "__main__":
    train_model()
