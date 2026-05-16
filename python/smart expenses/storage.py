import csv
import os

DEFAULT_FILE_PATH = "expenses.csv"

def ensure_file_exists(file_path):
    if not os.path.exists(file_path):
        try:
            with open(file_path, mode='w', newline='') as file:
                writer = csv.writer(file)
                writer.writerow(["date", "category", "amount", "description"])
        except Exception as e:
            print(f"Oops, couldn't create the file. Error: {e}")

def save_expense(date_str, category, amount, description, file_path=DEFAULT_FILE_PATH):
    ensure_file_exists(file_path)
    
    try:
        with open(file_path, mode='a', newline='') as file:
            writer = csv.writer(file)
            writer.writerow([date_str, category, amount, description])
            return True
    except Exception as e:
        print(f"Failed to save the expense: {e}")
        return False

def load_all_expenses(file_path=DEFAULT_FILE_PATH):
    ensure_file_exists(file_path)
    expenses_list = []
    
    try:
        with open(file_path, mode='r') as file:
            reader = csv.DictReader(file)
            for row in reader:
                row['amount'] = float(row['amount'])
                expenses_list.append(row)
    except Exception as e:
        print(f"Trouble reading the file: {e}")
        
    return expenses_list
