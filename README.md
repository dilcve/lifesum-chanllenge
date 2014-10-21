#Lifesum-Chanllenge

This app was made only for Lifesum Chanllege.

To search for food, use the search from field. After search returns some results you can save items locally.

In the "Offline products", only the saved items are displayed.

For the UI, I used: a card list style, with 2 options for show the data. To perform the request, I used a asyncTask. 
To perform DB operations, I used as Sugar ORM, because it's super easy and fast, in order to perform save, I used some async tasks (because these operations should be reflected on the UI).

##IF I HAD MORE TIME...

- implement the delete for offline list.
- show the details, with a good UI for it, because there are a lot of data, he could be confuse, if it wasn't show in the right way.
- implement caching for online searches.
- user more animations
- improve the UI.
- improve the feedbacks for the user.

Total time about: 6h 30min

## DEPENDENCIES

- SugarORM  - for handle with ORM stuffs -  https://github.com/satyan/sugar
- Adapter-kit - for save time when user lists - https://github.com/mobsandgeeks/adapter-kit
- Gson - for JSON <-> POJO conversions - https://code.google.com/p/google-gson/

