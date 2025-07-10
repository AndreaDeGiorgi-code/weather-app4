Documentazione del Progetto: Weather App

🌍 Introduzione Weather App è un'applicazione web che fornisce informazioni meteo in tempo reale per qualsiasi città. Utilizza Spring Boot per il back-end e consente agli utenti di cercare previsioni meteo per città selezionate.

L'applicazione si connette a un'API meteo esterna per raccogliere i dati aggiornati e visualizzarli in un'interfaccia semplice e user-friendly.

🛠️ Tecnologie Utilizzate Spring Boot: Framework per il back-end basato su Java.

Maven: Strumento di gestione delle dipendenze e build.

Docker: Per la containerizzazione dell'applicazione.

API meteo: L'applicazione si connette a un'API esterna per ottenere i dati meteo (ad esempio OpenWeatherMap).

Java: Linguaggio di programmazione principale.

🚀 Come Avviare il Progetto 📋 Prerequisiti Java 17 o superiore.

Maven.

Docker (opzionale, per l'uso di container).

📝 Passaggi Clonare il repository (o estrarre il progetto in una cartella locale):

bash

git clone <URL_del_tuo_repository> Naviga nella cartella del progetto:

bash

cd weather-app Esegui l'applicazione con Maven:

bash

./mvnw spring-boot:run O se stai usando Windows:

mvnw.cmd spring-boot:run Il progetto sarà ora accessibile su http://localhost:8080.

Opzionale: Avvio con Docker: Se hai Docker installato, puoi eseguire l'applicazione in un container:

Costruisci l'immagine Docker:

docker build -t weather-app .

Avvia il container:

docker run -p 8080:8080 weather-app

Verifica: Dopo aver avviato il progetto, apri un browser e naviga su http://localhost:8080 per vedere l'applicazione in esecuzione.

🗂️ Struttura del Progetto La struttura del progetto è la seguente:

css

weather-app/ │ ├── weather-app/ │ ├── .gitignore │ ├── docker-compose.yml │ ├── Dockerfile │ ├── HELP.md │ ├── mvnw │ ├── mvnw.cmd │ ├── pom.xml │ ├── src/ │ └── target/ │ ├── .vscode/ │ └── .git/ src/: Contiene il codice sorgente dell'applicazione.

main/java/: Codice Java per la logica dell'app.

main/resources/: Risorse come i file di configurazione.

pom.xml: File di configurazione di Maven che gestisce le dipendenze del progetto.

target/: Cartella generata durante la compilazione, contiene i file compilati.

docker-compose.yml e Dockerfile: File di configurazione per eseguire il progetto in un contenitore Docker.

🧑‍💻 API Endpoints Se il progetto espone API, ad esempio per ottenere le previsioni meteo, qui puoi descrivere gli endpoint.

/api/weather Metodo: GET

Descrizione: Restituisce le previsioni meteo per una città specificata.

Parametri:

city: Il nome della città per cui ottenere il meteo (es. "Torino").

Risposta:

200 OK: Restituisce un oggetto JSON con le informazioni meteo.

400 Bad Request: Se il nome della città non è valido o manca un parametro.

👨‍💻 Autori e Licenza Autore: Andrea De Giorgi

Licenza: MIT License
