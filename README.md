# FarmCollector

1. Create a Farm Record

Endpoint:

POST /farms

Description:

Creates a new farm record with planting details.

Request Body:

{
"name": "MyFarm",
"year": 2024,
"season": "SPRING",
"cropType": "Corn",
"plantingArea": 10.5,
"amountExpected": 50.0
}

Response:

201 CREATED

Returns the created farm record.

2. Retrieve All Farms

Endpoint:

GET /farms

Description:

Fetches a paginated list of farm records by farm name.

Query Parameters:

Parameter

Type

Default

Description

farmName

String

Required

Name of the farm to search

pageNo

int

0

Page number

pageSize

int

10

Page size

Response:

200 OK

Returns a paginated list of farm records.

3. Retrieve a Farm by ID

Endpoint:

GET /farms/{id}

Description:

Fetches a single farm record by its ID.

Path Parameter:

Parameter

Type

Description

id

Long

Unique ID of the farm

Response:

200 OK

Returns the farm record.

404 NOT FOUND if the farm ID does not exist.

4. Update Actual Harvested Amount

Endpoint:

PUT /farms/{id}

Description:

Updates the actual harvested amount for a given farm.

Path Parameter:

Parameter

Type

Description

id

Long

Unique ID of the farm

Query Parameter:

Parameter

Type

Description

actualAmount

Double

Actual harvested amount

Response:

200 OK

Returns the updated farm record.

400 BAD REQUEST if actual amount is negative.

5. Get Farm Report

Endpoint:

GET /farms/report

Description:

Generates a report comparing expected and actual harvest amounts for a farm.

Query Parameters:

Parameter

Type

Default

Description

farmName

String

Required

Name of the farm to fetch the report for

pageNo

int

0

Page number

pageSize

int

10

Page size

Response:

200 OK

Returns a paginated list of farm reports in the format:

{
"content": [
{
"expectedAmount": 50.0,
"actualAmount": 45.0
}
],
"pageable": {
"pageNumber": 0,
"pageSize": 10
},
"totalElements": 1,
"totalPages": 1
}

Error Handling

All endpoints return meaningful error messages for invalid requests:

400 BAD REQUEST: If input data is invalid.

404 NOT FOUND: If a requested farm ID is not found.

500 INTERNAL SERVER ERROR: If an unexpected error occurs.

