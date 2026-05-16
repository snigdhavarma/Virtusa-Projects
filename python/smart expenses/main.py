import sys
from expense_manager import add_new_expense, get_expenses, get_categories
from analytics import display_monthly_summary, show_pie_chart, generate_spending_insights

def print_menu():
    print("\n=== Smart Expense Tracker ===")
    print("1. Add a new expense")
    print("2. View all expenses")
    print("3. View monthly summary")
    print("4. See category breakdown (Pie Chart)")
    print("5. Get spending insights")
    print("6. Exit")
    print("=============================")

def main():
    print("Welcome to the Smart Expense Tracker!")
    
    while True:
        print_menu()
        user_choice = input("What would you like to do? (1-6): ")
        
        if user_choice == '1':
            date_input = input("Enter the date (YYYY-MM-DD): ")
            
            print(f"Suggested categories: {', '.join(get_categories())}")
            category_input = input("Category: ")
            
            amount_input = input("How much did you spend?: ₹")
            description_input = input("Brief description: ")
            
            add_new_expense(date_input, category_input, amount_input, description_input)
            
        elif user_choice == '2':
            all_expenses = get_expenses()
            if len(all_expenses) == 0:
                print("No expenses logged yet.")
            else:
                print("\n--- All Recorded Expenses ---")
                for exp in all_expenses:
                    print(f"{exp['date']} - {exp['category']} - ₹{exp['amount']} ({exp['description']})")
                    
        elif user_choice == '3':
            month_input = input("Enter the month you want to see (YYYY-MM): ")
            all_expenses = get_expenses()
            display_monthly_summary(all_expenses, month_input)
            
        elif user_choice == '4':
            all_expenses = get_expenses()
            show_pie_chart(all_expenses)
            
        elif user_choice == '5':
            all_expenses = get_expenses()
            generate_spending_insights(all_expenses)
            
        elif user_choice == '6':
            print("Thanks for using the tracker. See you next time!")
            break
            
        else:
            print("Hmm, I didn't understand that. Please type a number between 1 and 6.")

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nForce closed. Bye!")
        sys.exit(0)
