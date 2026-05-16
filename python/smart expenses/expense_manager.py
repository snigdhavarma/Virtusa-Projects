from storage import save_expense, load_all_expenses

VALID_CATEGORIES = ["Food", "Travel", "Bills", "Entertainment", "Shopping", "Other"]

def add_new_expense(date_str, category, amount_str, description):
    try:
        amount = float(amount_str)
    except ValueError:
        print("Wait, that doesn't look like a valid number for the amount.")
        return False
        
    if amount <= 0:
        print("Amount should be greater than zero. No free money here!")
        return False
        
    clean_category = category.capitalize()
    if clean_category not in VALID_CATEGORIES:
        print(f"Warning: '{clean_category}' is not in our standard list, but we'll allow it.")
        
    success = save_expense(date_str, clean_category, amount, description)
    if success:
        print("Awesome, expense added successfully.")
        
    return success

def get_expenses():
    return load_all_expenses()

def get_categories():
    return VALID_CATEGORIES
