import json

class RestAPI:
    def __init__(self, database=None):
        self.db = database or {"users": []}

    def get(self, url, payload=None):
        if url == "/users":
            if payload:
                names = json.loads(payload).get("users", [])
                users = [u for u in self.db["users"] if u["name"] in names]
                return json.dumps({"users": users})
            return json.dumps(self.db)

    def post(self, url, payload=None):
        data = json.loads(payload) if payload else {}

        if url == "/add":
            new_user = {
                "name": data["user"],
                "owes": {},
                "owed_by": {},
                "balance": 0.0
            }
            self.db["users"].append(new_user)
            return json.dumps(new_user)

        if url == "/iou":
            lender_name = data["lender"]
            borrower_name = data["borrower"]
            amount = data["amount"]

            lender = next(u for u in self.db["users"] if u["name"] == lender_name)
            borrower = next(u for u in self.db["users"] if u["name"] == borrower_name)

            if borrower_name in lender["owes"]:
                old_debt = lender["owes"][borrower_name]
                if amount > old_debt:
                    del lender["owes"][borrower_name]
                    del borrower["owed_by"][lender_name]
                    new_amount = amount - old_debt
                    lender["owed_by"][borrower_name] = lender["owed_by"].get(borrower_name, 0) + new_amount
                    borrower["owes"][lender_name] = borrower["owes"].get(lender_name, 0) + new_amount
                elif amount < old_debt:
                    lender["owes"][borrower_name] -= amount
                    borrower["owed_by"][lender_name] -= amount
                else:
                    del lender["owes"][borrower_name]
                    del borrower["owed_by"][lender_name]
            else:
                lender["owed_by"][borrower_name] = lender["owed_by"].get(borrower_name, 0) + amount
                borrower["owes"][lender_name] = borrower["owes"].get(lender_name, 0) + amount

            lender["balance"] += amount
            borrower["balance"] -= amount

            affected_users = sorted([lender, borrower], key=lambda x: x["name"])
            return json.dumps({"users": affected_users})