## Problem Domain
There is advertisement trading platform. Users can place advertisement delivery in the platform, called placement.
 
 A placement consists of

- Id
- Placement Start date
- placement End date
- Cost Per Mile (CPM)

For example, 1,Sports,11/1/20,11/30/20,5 is a valid placement.

A delivery is a delivery of the placed advertisement request. A delivery consists of 

- Placement Id
- Delivery Date
- Impression

For example, a delivery `1,11/1/2020,33427` means the delivery for placement 1 on 11/1/2020 has 33247 impressions on the end customers.

Now, as the platform, we need to calculate the total charge for a placement for our customers. 
The formula we use is `Total Impression Per Placement * CPM / 1000`. 

## Data Set
- /resource/delivery.csv
- /resource/placements.csv

## Existing Function
1. Read the data from CSV to in memory database. 
2. Delivery database schema
3. Placement database schema

## TODO
1. Write a function takes in a placement, start date and end date. Return the total impressions within the given date range for the given placement. 
2. Write a function takes in a placement, return the total cost we need to charge for the customer using the above cost formula. 
3. Write a function to calculate the total cost we need to charge for all placements. (Challenge)