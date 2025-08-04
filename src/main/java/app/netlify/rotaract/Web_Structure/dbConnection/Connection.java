package app.netlify.rotaract.Web_Structure.dbConnection;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

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
            CREDENTIALS = getCredentials(GoogleNetHttpTransport.newTrustedTransport());
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        String CREDENTIALS_FILE_PATH = "/credentials.json";

        InputStream in = Connection.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        String TOKENS_DIRECTORY_PATH = "tokens";

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver.Builder().setPort(52284).build())
                .authorize("user");
    }

    public Sheets getSheetsService(final NetHttpTransport HTTP_TRANSPORT, final Credential CREDENTIALS, final String APPLICATION_NAME, final JsonFactory JSON_FACTORY){
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, CREDENTIALS)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    public Sheets getSheetsService() throws GeneralSecurityException, IOException {
        String APPLICATION_NAME = "Google DataBaseUsingSheets API Java Web-Structure-Rotaract";
        return this.getSheetsService(GoogleNetHttpTransport.newTrustedTransport(), this.CREDENTIALS, APPLICATION_NAME, this.JSON_FACTORY);
    }


    public static void main(String... args) throws IOException, GeneralSecurityException {

        var con = new Connection();
        Sheets service = con.getSheetsService();
        String range = "teste!A2:E";

        final String spreadsheetId = "1qt1Ql7xTzxc5SiRo3tB3-lpBc7zBM_4l0vXqwLPCnSs";

        System.out.println("Retrieving values from spreadsheet\n: " + service.spreadsheets().get(spreadsheetId));

        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name, Major");
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                System.out.printf("%s, %s\n", row.get(0), row.get(1));
            }
        }
    }


    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }
}
