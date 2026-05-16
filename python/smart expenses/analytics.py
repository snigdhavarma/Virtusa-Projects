import matplotlib.pyplot as plt

def get_category_totals(expenses_list):
    totals = {}
    
    for expense in expenses_list:
        cat = expense['category']
        amt = expense['amount']
        
        if cat in totals:
            totals[cat] = totals[cat] + amt
        else:
            totals[cat] = amt
            
    return totals

def display_monthly_summary(expenses_list, target_month):
    monthly_total = 0.0
    found_any = False
    
    print(f"\n--- Summary for {target_month} ---")
    
    for expense in expenses_list:
        if expense['date'].startswith(target_month):
            print(f"{expense['date']} | {expense['category']} | ₹{expense['amount']} | {expense['description']}")
            monthly_total = monthly_total + expense['amount']
            found_any = True
            
    if not found_any:
        print("Looks like you didn't spend anything this month. Good job!")
    else:
        print("-------------------------------")
        print(f"Total spent this month: ₹{monthly_total:.2f}")

def show_pie_chart(expenses_list):
    category_totals = get_category_totals(expenses_list)
    
    if not category_totals:
        print("No data available to create a chart.")
        return
        
    labels = []
    sizes = []
    
    for cat, total in category_totals.items():
        labels.append(cat)
        sizes.append(total)
        
    try:
        plt.figure(figsize=(8, 6))
        plt.pie(sizes, labels=labels, autopct='%1.1f%%', startangle=140)
        plt.title('Expense Breakdown by Category')
        plt.axis('equal')
        plt.show()
    except Exception as e:
        print("Uh oh, couldn't load the chart. Do you have matplotlib installed?")

def generate_spending_insights(expenses_list):
    category_totals = get_category_totals(expenses_list)
    
    if not category_totals:
        print("Need some expenses first before I can give you insights.")
        return
        
    highest_category = ""
    highest_amount = 0.0
    
    for cat, amount in category_totals.items():
        if amount > highest_amount:
            highest_amount = amount
            highest_category = cat
            
    print(f"\n--- Spending Insights ---")
    print(f"Yikes! Your biggest money pit is '{highest_category}', eating up ₹{highest_amount:.2f}.")
    
    if highest_category == "Food":
        print("Tip: Maybe try cooking at home a bit more? Eating out adds up fast.")
    elif highest_category == "Shopping":
        print("Tip: Ask yourself 'Do I really need this?' before buying.")
    elif highest_category == "Entertainment":
        print("Tip: Look for free events in your city or share subscriptions with friends.")
    else:
        print("Tip: See if you can cut back a little bit in this area next month.")
