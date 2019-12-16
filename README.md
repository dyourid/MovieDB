# MovieDB
It’s a simple application for showcasing movies. This application uses The Movie Database (TMDb) API to get the information
from and show them into two sections where you can view them into the ”popular movies” and ”top-rated movies”. Using the
API, the application fetches the movie list and shows the poster of the movies into these categories so that the app can mimic
cineplex feeling. When the user touches on a movie, it will open new activity and will show the description of that movie and
show the rating of that movie. A poster of that movie is there also. A search bar is there to let the user search for a movie.
Because I attempted this for the first time to develop some Android applications, I thought of designing something I will be using
daily. While designing, I thought of those functionalities, which will make my movie selection easy, and I tried to will get the
best out of my experience in movie searching. Developing a good functional movie showcasing app requires great expertise and
practice. Because of my limited knowledge and practice, I succeeded in implementing only a few features into the app, and there
is a lot of room for improvement,
# Design
The
essential part of this app is The Movie Database (TMDb) API. To show all the components of movies, The Movie Database
(TMDb) API is used. Upon request, API is providing the data, and the app is manifesting them into different categories. This
can be divided into three parts:
1: Movie List: The application makes two requests to the Movie DB API when the app is launched. One of them gets popular
movies, and another one gets the top-rated films from the API. These data come in JSON format and is parsed within the
application to generate the Movie objects used to show them in the list views. The search result list is hidden at this moment.
However, if a user types something in the search box and presses search, then the search result list view is shown with the
movie result that came back from the API. The list is shown using a grid view with two columns. A loader is made visible
when the application is making the network call to fetch the movies. All the film images shown in the lists are clickable, and
upon clicking in any of them, it will show an entirely new view with the details of the movie.
2: Movie Description: This view shows the movie’s information in a separate view. Information is passed through as data of
extra intent. This view shows the name of that movie, the poster of that movie with a description and user rating is provided
at the bottom of the description As we already have all the information available, we did not have to make another request to
the API. The main result view activity is registered as the parent activity of this activity. Therefore a back button is presented
automatically by the operating system.
3: Search: If the user is looking for a particular movie that he wants to know more about, he will be able to search for the
film by name. Upon finding that movie, he can see the description and rating of that movie. The search result list and label
is hidden until your searches for something to save screen real estate. A network call is triggered as soon as the user hits the
search button, and a result set is fetched from the API to show those in the search result list.
The flowchart shows how the application works.
![alt text](https://github.com/dyourid/MovieDB/blob/master/1.png)
![alt text](https://github.com/dyourid/MovieDB/blob/master/2.png)
![alt text](https://github.com/dyourid/MovieDB/blob/master/3.png)
![alt text](https://github.com/dyourid/MovieDB/blob/master/4.png)
![alt text](https://github.com/dyourid/MovieDB/blob/master/6.png)
# conclusion
People make time out of their busy schedule for a movie, and if that movie turns out to be a bad one, it’s a significant
setback for any person. If the properties such as description, rating, reviews are considered, it will minimize the risk for
a bad experience. This app will make sure you are selecting the right film for you.
