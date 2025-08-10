package app.netlify.rotaract.Web_Structure.dbConnection;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class Connection implements IConnection{

    private final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private final Credential CREDENTIALS;

    public Connection() {
        try {
            CREDENTIALS = getCredentials();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }


    private GoogleCredential getCredentials() throws IOException, GeneralSecurityException {
        // Tenta pegar o JSON da service account da variável de ambiente
        String serviceAccountJson = System.getenv("GOOGLE_SERVICE_ACCOUNT_JSON");

        InputStream credentialsStream;

        if (serviceAccountJson != null) {
            credentialsStream = new ByteArrayInputStream(serviceAccountJson.getBytes());
        } else {
            // Se não tiver na variável, tenta pegar do arquivo local
            credentialsStream = Connection.class.getResourceAsStream("src/main/resources/account_service.json");
            if (credentialsStream == null) {
                throw new FileNotFoundException("Resource not found: service-account.json");
            }
        }

        return GoogleCredential.fromStream(credentialsStream)
                .createScoped(SCOPES);
    }

    public Sheets getSheetsService() throws GeneralSecurityException, IOException {
        String APPLICATION_NAME = "Google DataBaseUsingSheets API Java Web-Structure-Rotaract";
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                CREDENTIALS)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }
}
