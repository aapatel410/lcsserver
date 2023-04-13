import requests

# Define test cases
test_cases = [
    {
        "name": "Empty body test",
        "data": {},
        "expected_status": 400,
        "expected_response": {"error": ["Missing Post Body"]}
    },
    {
        "name": "Empty setOfStrings test",
        "data": {"setOfStrings": []},
        "expected_status": 400,
        "expected_response": {"error": ["setOfStrings should not be empty"]}
    },
    {
        "name": "Non-unique setOfStrings test",
        "data": {"setOfStrings": [{"value": "comcast"}, {"value": "comcast"}]},
        "expected_status": 400,
        "expected_response": {"error": ["setOfStrings must contain unique values"]}
    },
    {
        "name": "Single string test",
        "data": {"setOfStrings": [{"value": "comcast"}]},
        "expected_status": 200,
        "expected_response": {"lcs": ["comcast"]}
    },
    {
        "name": "Multiple strings test",
        "data": {"setOfStrings": [{"value": "comcast"}, {"value": "comcastic"}, {"value": "broadcaster"}]},
        "expected_status": 200,
        "expected_response": {"lcs": ["cast"]}
    }
]

# Define server URL
server_url = "http://localhost:8080/lcs"

# Test each case
for case in test_cases:
    print(f"Running test case: {case['name']}")
    print(f"Input data: {case['data']}")
    response = requests.post(server_url, json=case["data"])
    actual_status = response.status_code
    actual_response = response.json()
    if actual_status == case["expected_status"] and actual_response == case["expected_response"]:
        print("Test passed")
    else:
        print("Test failed")
        print(f"Expected status code: {case['expected_status']}")
        print(f"Actual status code: {actual_status}")
        print(f"Expected response: {case['expected_response']}")
        print(f"Actual response: {actual_response}")
    print("--------")
