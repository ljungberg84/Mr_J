# Projekt_Mr_J

This is an aggregator application for entertainment streaming services that uses **Java** and **selenium** to gather information on titles available at given service.
The purpose of this program is to save you some precious time while looking for the next film or tv-series to watch. It sure is troublesome to have to go and search all services separately? 

Our program allows you to search for a title, simultaneously on all the available streaming services so you don't have to search in all of them separately. So after you have registered your account details in the program you should be able to search for a title and find out where it exists. Then with just one click on the result, you are taken to the webpage of the streaming service and you can immediately start watching. At the moment our program has support for following platforms: HBO, Netflix, Viaplay and SVTplay. Netflix requires username and password to be queried.
If you are using the command line all you need to do to run our program, is: 
--clone the repo in your computer (git clone )
--compile and package with maven (mvn compile, mvn package)
--and run it (mvn exec:java)

When you run the program you should be able to type in your account details for the various sites. Netflix requires log-in in order to search. HBO and Viaplay do not require log-in to search but it is of course required in order to watch a show. SVT is the only platform which doesn't require log-in. The program uses cookies which allow a user to remain logged in.

We hope you enjoy it!

                                                                                                   Bartek, Carl, Martin, Petros
