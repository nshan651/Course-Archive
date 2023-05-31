import pymongo
import datetime, pprint
from pymongo import MongoClient

#Create connection (get connection string in Atlas) 
client = pymongo.MongoClient("mongodb+srv://rickross99:Platypus99@cluster0.aiqfe81.mongodb.net/?retryWrites=true&w=majority")

#Getting a database called test_database
db = client.sample_airbnb

#Getting a collection called posts
listings = db.listingsAndReviews

print('______________________________________')
print('Query 1 Results:')
print('Select the top five largest numbers of listings')
print('         (host_name, host_total_listings_count)')
query=listings.find({},{
   "host.host_name": 1,
   "host.host_total_listings_count": 1}
       ).sort("host.host_total_listings_count",-1).limit(5)

for quer in query:
        pprint.pprint(quer)
print('______________________________________')

print('______________________________________')
print('Query 2 Results:')
print('Select the top 10 listings with Cable TV, Wifi, and Iron amenities; sort by minimum nights descending')
print('         (_id, minimum_nights, name, property_type)')
query=listings.find({
    "$and": [{
       "amenities" : "Cable TV" 
     },{ "amenities" :  "Wifi" 
       },{ "amenities" :  "Iron" 
   }]
    },{
   "_id": 1,
   "minimum_nights": 1,
   "name": 1,
   "property_type": 1,
}
).sort("minimum_nights", -1).limit(10)

for quer in query:
        pprint.pprint(quer)
print('______________________________________')

print('______________________________________')
print('Query 3 Results:')
print('Select the maxium number of nights')
print('         (_id, name, property_type, maximum_nights)')
query=listings.find({},{
   "_id": 1,
   "name": 1,
   "property_type": 1,
   "maximum_nights": 1}
       ).limit(1).sort("maximum_nights",-1)

for quer in query:
        pprint.pprint(quer)
print('______________________________________')

print('______________________________________')
print('Query 4 Results:')
print('Select the five listings with between 5 and 8 beds inclusive; sort descending')
print('         (_id, accomodates, bedrooms, beds, name, property_type)')
query=listings.find({ "$and": [{"accommodates" : {"$gte": 5,"$lte":8}},{"beds": {"$gte" : 5}}]},
   {"_id": 1,
   "name": 1,
   "property_type": 1,
   "accommodates": 1,
   "beds":1,
   "bedrooms":1
   }
       ).sort("accommodates",-1).limit(5)

for quer in query:
        pprint.pprint(quer)
print('______________________________________')

print('______________________________________')
print('Query 5 Results:')
print('Select the top 5 listings where the number of bathrooms is greater than the number of bedrooms; sort descending by bathrooms')
print('         (_id, bathrooms, bedrooms, beds, name, property_type)')
query=listings.find({ "$expr": { "$gt": [ "$bathrooms" , "$bedrooms" ] } },
{"_id": 1,
   "name": 1,
   "property_type": 1,
   "bathrooms": 1,
   "bedrooms":1,
    "beds":1
   }
       ).sort("bathrooms",-1).limit(5)

for quer in query:
        pprint.pprint(quer)

print('______________________________________')

print('______________________________________')
print('Query 6 Results:')
print('Select the top 10 reviews; sort by review rating descending')
print('         (_id, name, property_type, review_scores.review_scores_rating, summary)')
query=listings.find({},{
   "_id": 1,
   "name": 1,
   "property_type": 1,
   "summary": 1,
   "review_scores.review_scores_rating":1
   }
       ).sort("review_scores.review_scores_rating",-1).limit(10)

for quer in query:
        pprint.pprint(quer)
print('______________________________________')
