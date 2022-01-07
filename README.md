# vrachtwagen-service
Dit is de vrachtwagen-service back-end voor het project Advanced Programming Topics van Jules Debbaut en Walid Amhaini uit 3ITF. Deze service behandelt voornamelijk de gegevens van een vrachtwagen, bijvoorbeeld nummerplaat, het bedrijf waartoe een vrachtwagen behoort en bovendien het merk, model en bouwjaar van de vrachtwagen. 
De back-end database maakt hier gebruik van MongoDB. Deze microservice voorziet van 6 GET-endpoints, 1 POST-endpoint, 1 PUT-endpoint en 1 DELETE-endpoint. 

# GET /vrachtwagens
Door deze request te maken haal je alle vrachtwagens op die in de database zitten.

![image](https://user-images.githubusercontent.com/57659236/148540122-d497df25-30fe-40cf-8f52-c879fecb90e7.png)


# GET /vrachtwagens/nummerplaat/{nummerplaat}
Door een nummerplaat in deze request mee te geven, haal je 1 vrachtwagen op met exact die nummerplaat. 

![image](https://user-images.githubusercontent.com/57659236/148540216-7d41113e-8618-45b5-be21-665853132752.png)


# GET /vrachtwagens/bouwjaar/{bouwjaar}
Door in deze request een bouwjaar mee te geven, zal u alle vrachtwagens meekrijgen met het gegeven bouwjaar.

![image](https://user-images.githubusercontent.com/57659236/148540274-db358dfb-26d3-4bea-b594-bc1ce159e726.png)


# GET /vrachtwagens/merk/{merk}
Door in deze request een merk mee te geven, zal u alle vrachtwagens meekrijgen met een gegeven merk.

![image](https://user-images.githubusercontent.com/57659236/148540307-6e6ffe6d-07e0-4b5b-970d-f50d70114771.png)


# GET /vrachtwagens/model/{model}
Door in deze request een model van een vrachtwagen mee te geven, zal u alle vrachtwagens van dit soort te zien krijgen.

![image](https://user-images.githubusercontent.com/57659236/148540349-00e5facd-8a47-4ddd-acb7-111135ae9283.png)


# POST /vrachtwagens
Deze request wordt gebruikt om vrachtwagens aan de back-end toe te voegen.
![image](https://user-images.githubusercontent.com/57659236/148540624-e7576107-efdb-4700-aabc-987dc3d81826.png)



# PUT /vrachtwagens/nummerplaat/{nummerplaat}
De PUT request gebeurt hier op basis van het nummerplaat van een vrachtwagen, aangezien deze uniek is voor elke vrachtwagen. U geeft hier dus gewoon een nummerplaat mee voor de vrachtwagen die u wilt bewerken.
![image](https://user-images.githubusercontent.com/57659236/148540812-2e3d053e-a456-46ef-9c3a-08d08d40f0f1.png)
![image](https://user-images.githubusercontent.com/57659236/148540827-5861d719-231c-4161-8817-5e7fed1a9d3a.png)



# DELETE /vrachtwagens/nummerplaat/{nummerplaat}
Ook de DELETE request gebeurt op dezelfde manier. U geeft hier dus de nummerplaat van de vrachtwagen mee die u uit het systeem wilt verwijderen.
![image](https://user-images.githubusercontent.com/57659236/148540853-3ea41537-5968-4392-95ff-0b663cee0eef.png)


