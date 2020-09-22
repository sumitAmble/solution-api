# solution-api
Exercise 
This api is build over the hacker-news api from where i am getting the latest stories and comments
Link for the hacker-new api https://github.com/HackerNews/API

I am creating three apis 
  1) GET /best-stories- this api will return the top 10 stories based on the score 
  2) GET /{story-id}/comments-  this api return top ten comments based on the reponses a comment has received
  3) GET /past-stories - this api will return past top stories.
  
The hacker news api has low latency so i have created seperate logic for that by which my cron job which will call the hacker-news api after 13 mins and it will automatically refresh the list of top 10 best stories as well as the comments and past-stories. However if hacker-news api break-down or any exception occure due to repsonse time it will not affect any of my apis.
