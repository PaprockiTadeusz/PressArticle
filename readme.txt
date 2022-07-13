## Press Article Application
This application is a simple REST API for press articles. It's written with a "Spring" framework. It can help Magazines to manage their articles in the databases.

*How to start the application?*
  1. Clone the repository  
  2.  Run the 'mvnw spring-boot:run' command  
  3. The application will start on port 8080

## Press Article
Press article contains:

 - Title and Content
 - Date of publication
 - Magazine name
 - Author
 
 ## Features
 
 - **Getting all articles sorted descending by date of publication.**
 GET /articles
 
 - **Getting single article by its ID.**
 GET /articles/{id}
 
 - **Saveing new Article to database.**
 POST /articles
   Creates a new press article. The request body should contain the following fields:  
   - content (publication title and publication content) 
    - publication date (date)  
    - the name of the journal
    -  author of the article (name and surname)
 - **Full edit of Article that is in the databse.**
  PUT /articles/{id}  
  Updates the press article with the given id. The request body should contain the following fields: 
   - content (publication title and publication content)  
   - publication date (date)  
   - the name of the journal  
   - author of the article (name and surname)
 - **Partially update of article in the database.**
 - **Deleteing Article.**
 
 
