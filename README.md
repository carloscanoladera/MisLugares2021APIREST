# MisLugares2021APIREST

The aim of this project is to offer an API Rest for the android app MisLugares from the book El gran Libro de Android.
In the Folder RecursosParaAndroidYSCRIPTSQL, you could find the DataBase script and also a Java class LugaresBDApiRest. This class 
belongs to the Android Studio project MisLugares. It performs the access to the APIRest following the interface RepositorioLugares provided by the 
application to guarantee a smooth migration to different data access implementations. In spite of having another version with Retrofit2, I prefer to use the 
HttpURLConnection Java Class to send requests to the Server for didactical purposes. I believe this is a more convenient for the students to understand the API Rest
principles.

Notice that in the LugaresBDApiRest you can find a constant URL_BASE. Replace the current local Ip with your own and do not use localhost.
