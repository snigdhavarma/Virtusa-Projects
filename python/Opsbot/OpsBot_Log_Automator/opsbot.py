import os
import datetime

def process_logs(fname):
    if not os.path.exists(fname):
        print("file not found")
        return

    counts = {
        "ERROR": 0,
        "CRITICAL": 0,
        "WARN": 0
    }

    problems = []

    try:
        with open(fname, 'r') as f:
            for line in f:
                if "ERROR" in line:
                    counts["ERROR"] += 1
                    problems.append(line)
                elif "CRITICAL" in line:
                    counts["CRITICAL"] += 1
                    problems.append(line)
                elif "WARN" in line:
                    counts["WARN"] += 1
                    problems.append(line)
    except Exception as e:
        print("error reading file:", e)
        return

    today = datetime.datetime.now().strftime("%Y-%m-%d")
    report = os.path.join(os.path.dirname(fname), f"security_alert_{today}.txt")

    if len(problems) > 0:
        try:
            with open(report, 'w') as out_file:
                out_file.write(f"Summary for {today}\n")
                out_file.write(f"Errors: {counts['ERROR']}\n")
                out_file.write(f"Criticals: {counts['CRITICAL']}\n")
                out_file.write(f"Warnings: {counts['WARN']}\n")
                out_file.write("\nDetails:\n")
                out_file.write("------------------------------\n")
                
                for line in problems:
                    out_file.write(line)
            
            size = os.path.getsize(report)
            print(f"file created: {report}")
            print(f"size: {size} bytes")
            print("counts:", counts)
            
        except Exception as e:
            print("failed to save report:", e)
    else:
        print("no alerts found")

if __name__ == '__main__':
    folder = os.path.dirname(os.path.abspath(__file__))
    process_logs(os.path.join(folder, "server.log"))
