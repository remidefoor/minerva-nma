# todo

- convert activity main to linear layout
- parse response google books api
- max width book title books page
- padding books recycler view (item decoration)
- replace mock data:
  - books recycler view
  - notes recycler view


# validate

- books fragment xml tree
- notes fragment xml tree

# topics

- styles
- themes

# questions

- Nu gebruik ik voor icoontjes die knoppen zijn image views, is dit correct of bestaat er hier een geschikter element voor?

- view models:

  - Nu maakt elk fragment gebruik van een view model ook al is er niet echt "live" data, maar verzamel ik bijvoorbeeld gebruikersinvoer. Is dit correct of kan ik beter de waarden opvragen in mijn fragment?
  - Is het beter om objecten of individuele properties te gebruiken?
  - Moet de data in live data zijn (zie bijvoorbeeld book view model)?

- Hoe maak ik een coroutine in een fragment?

- Ziet u een pagina om nieuwe boeken toe te voegen als een fragment binnen de main activity of als een nieuwe activity?

- Zou u a.u.b. eens de XML van mijn verschillende activeiten en fragmenten willen nakijken om te weten of ik een goede structuur heb en de juiste layouts en views gebruik?

- De on click listeners op de text views in mijn auth fragmenten luisteren niet.

- Hoe voeg ik click listeners met navigatie toe aan mijn bottom navigation menu?

- Ik kan geen marges instellen in het layout tablad voor elementen in een constraint layout.

  ![](.\notes_assets\images\constraint_layout_manager.png)

- Hoe parse ik een complexe API response waar ik slechts enkele velden nodig van heb met Moshi?

  ![](.\notes_assets\images\google_books_API_response.png)

  