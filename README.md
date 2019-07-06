# weather
A weather App demo using open weather map api.

<img src="https://github.com/wuhao028/weather/blob/master/screenshots/1.gif" width="30%" height="30%" />           <img src="https://github.com/wuhao028/weather/blob/master/screenshots/2.gif" width="30%" height="30%" />           <img src="https://github.com/wuhao028/weather/blob/master/screenshots/3.gif" width="30%" height="30%" />

- This app is based on MVP pattern and retrofit

- As limited time, I've only wrote the unit test for Util.class, report is under weather/report/index.html,
coverage is more than 80%.

- an apk is under root directory ready to install

Features :

- Showing cities's weather and fives days weather forecast
- Displaying icons
- City search and show the weather of search result
- Pull to refresh weather
- Animation for the wind image
- Sticky header animation
- Using the phone’s location to detect the default city
- Setting the windwindowBackground for MainActivity makes the loadng more smooth

Improvements need to do:

- optimize the error page including network error and data error page etc.
- wirte more unit test
- add city management, allow user to add and delete cities. 
- store the data in local device
- test on more different size screens 


