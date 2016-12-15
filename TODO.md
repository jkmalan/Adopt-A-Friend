Interface:
- ~~LoginPage~~
- ~~CreateProfilePage~~ Needs layout,
- ~~HomePage~~
- ~~EditProfilePage~~ Needs layout,
- ~~CreateListingPage~~ Needs layout, Needs connections
- EditListingPage
- ListingPage
- SearchPage
- SearchListingPage (Search listings)
- UserListingPage (User's listings)

Photo Saving:
- Successfully saving images to the database

Password Storage:
- Successfully and securely storing passwords in the database




Incomplete:
- Overall
  - Double check all exception handling
  - Reformat and properly indent
  - Clear out test classes and viewers
- Interface
  - MainPage
    - Hold onto user object
  - CreateListing
    - Create listings
    - Functional layout
  - EditListing
    - Edit listings
    - Functional layout
- Listing
- User

Complete:
- AppEngine/Main
  - Properly calls functions
  - Properly shuts down
- Database
  - Working SQL queries
  - Working database connection
  - Proper database generation
  - Error handling and checking
- User
  - Validate logins
  - Retrieve and insert new users
  - UserCache maintenance
- Listing
  - Retrieve and insert new listings
  - ListingCache maintenance
- Interface
  - LoginFrame
    - Validate logins
    - Proper layout
  - ProfileFrame
    - Create users
    - Functional layout
    
Known Issues:
- No password processing/security/storage