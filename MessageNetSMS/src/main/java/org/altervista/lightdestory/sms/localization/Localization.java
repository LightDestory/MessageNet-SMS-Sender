package org.altervista.lightdestory.sms.localization;

import java.util.HashMap;
import org.altervista.lightdestory.sms.config.Configuration;

public class Localization {
    
    private static Localization instance = new Localization();
    private HashMap<String,HashMap<String,String>> Locales;
    private static String[] Langs;
    public static final String SENDER_NOTE_ID = "SENDER_NOTE", CURRENT_USER_ID = "CURRENT_USER", JOP_W_TITLE_ID = "JOP_W_TITLE", JOP_E_TITLE_ID = "JOP_E_TITLE",
            JOP_I_TITLE_ID = "JOP_I_TITLE", CONTACTS_ID = "CONTACTS", SMS_TAB_ID = "SMS_TAB" ,CONTACT_TAB_ID = "CONTACT_TAB", SETTING_TAB_ID = "SETTING_TAB", 
            SETTING_TAB_USERNAME_ID = "SETTING_TAB_USERNAME", SETTING_TAB_SENDER_ID = "SETTING_TAB_SENDER", SETTING_TAB_PASSWORD_ID = "SETTING_TAB_PASSWORD",
            SETTING_TAB_ACCOUNT_ID = "SETTING_TAB_ACCOUNT", JOP_SETTING_TAB_INVALID_ACCOUNT_ID = "SETTING_TAB_INDALID_ACCOUNT", SETTING_TAB_CURRENT_API_ID = "SETTING_TAB_CURRENT_API",
            SETTING_TAB_NEW_API_ID = "SETTING_TAB_NEW_API", SETTING_TAB_API_ID = "SETTING_TAB_API", JOP_SETTING_TAB_INVALID_API_ID = "SETTING_TAB_INVALID_API" ,SETTING_TAB_RESPONSE_ID = "SETTING_TAB_RESPONSE", 
            SETTING_TAB_REPORT_ID = "SETTING_TAB_REPORT", SETTING_TAB_REPORT_ENABLE_ID = "SETTING_TAB_REPORT_ENABLE", SETTING_TAB_REPORT_DISABLE_ID = "SETTING_TAB_REPORT_DISABLE", SETTING_TAB_SEND_ID = "SETTING_TAB_SEND",
            SETTING_TAB_SEND_GROUP_ID = "SETTING_TAB_SEND_GROUP", SETTING_TAB_SEND_SINGLE_ID = "SETTING_TAB_SEND_SINGLE", SETTING_TAB_LANGUAGE_ID = "SETTING_TAB_LANGUAGE", 
            SETTING_TAB_SAVE_CONF_ID = "SETTING_TAB_SAVE_CONF", SETTING_TAB_COST_ID = "SETTING_TAB_COST", SETTING_TAB_COST_TEXT_ID = "SETTING_TAB_COST_TEXT", SETTING_TAB_COST_CHECK_ID = "SETTING_TAB_COST_CHECK",
            SETTING_TAB_COST_SET_ID = "SETTING_TAB_COST_SET", SETTING_TAB_THEMES_ID = "SETTING_TAB_THEMES", CONTACT_TAB_ADD_ID = "CONTACT_TAB_ADD", CONTACT_TAB_ADD_ACTION_ID = "CONTACT_TAB_ADD_ACTION", 
            CONTACT_TAB_CSV_ID = "CONTACT_TAB_CSV_ID", CONTACT_TAB_CSV_FORMAT_ID = "CONTACT_TAB_CSV_FORMAT", CONTACT_TAB_CSV_LOAD_ID = "CONTACT_TAB_CSV_LOAD", 
            CONTACT_TAB_CSV_SAVE_ID = "CONTACT_TAB_CSV_SAVE", CONTACT_TAB_MANAGE_ID = "CONTACT_TAB_MANAGE", CONTACT_TAB_MANAGE_DEL_ID = "CONTACT_TAB_MANAGE_DEL", 
            CONTACT_TAB_MANAGE_DELALL_ID = "CONTACT_TAB_MANAGE_DELALL", CONTACT_TAB_MANAGE_SWAP_ID = "CONTACT_TAB_MANAGE_SWAP", CONTACT_TAB_MANAGE_SWAPALL_ID = "CONTACT_TAB_MANAGE_SWAPALL",
            CONTACT_TAB_TABLE_NAME_ID = "CONTACT_TAB_TABLE_NAME", CONTACT_TAB_TABLE_SURNAME_ID = "CONTACT_TAB_TABLE_SURNAME", CONTACT_TAB_TABLE_PHONE_ID = "CONTACT_TAB_TABLE_PHONE",
            VIEWER_TITLE_ID = "VIEWER_TITLE", VIEWER_SMS_TEXT_ID = "VIEWER_SMS_TEXT", SMS_TAB_SENDING_ID = "SMS_TAB_SENDING", SMS_TAB_SEND_ID = "SMS_TAB_SEND",
            SMS_TAB_SMS_SPACE_ID = "SMS_TAB_SMS_SPACE", SMS_TAB_TEXT_LENGTH_ID = "SMS_TAB_TEXT_LENGTH", SMS_TAB_NUM_SMS_ID = "SMS_TAB_NUM_SMS", SMS_TAB_OVERLIMIT_ID = "SMS_TAB_OVERLIMIT", SMS_TAB_COST_ID = "SMS_TAB_COST",
            SMS_TAB_CUSTOM_NOTE_ID = "SMS_TAB_CUSTOM_NOTE", DATABASE_ADD_CONTACT_ID = "DATABASE_ADD_CONTACT", DATABASE_OVERRIDE_CONTACT_ID = "DATABASE_OVERRIDE_CONTACT", DATABASE_NO_OVERRIDE_CONTACT_ID = "DATABASE_NO_OVERRIDE_CONTACT",
            CHANGE_LANG_ID = "LANG_CHANGE", CHANGE_ACCOUNT_ID = "CHANGE_ACCOUNT", CHANGE_API_ID = "CHANGE_API", CHANGE_COST_ID = "CHANGE_COST", CHANGE_RESPONSE_ID = "CHANGE_RESPONSE", CHANGE_THEME_ID = "CHANGE_THEME",
            DATABASE_ADD_TEMPLATE_ID = "DATABASE_ADD_TEMPLATE", DATABASE_LOAD_TEMPLATE_ID = "DATABASE_LOAD_TEMPLATE", DATABASE_DELETE_TEMPLATE_ID = "DATABASE_DELETE_TEMPLATE", DATABASE_DELETE_TEMPLATES_ID = "DATABASE_DELETE_TEMPLATES",
            DATABASE_SHOW_TEMPLATES_ID = "DATABASE_SHOW_TEMPLATES", DATABASE_DOUBLE_CLICK_ID = "DATABASE_DOUBLE_CLICK", DATABASE_SHOW_ALL_SMS_SENT_ID = "DATABASE_SHOW_ALL_SMS_SENT",
            CONTACT_TAB_DATABASE_SAVECT_ID = "CONTACT_TAB_DATABASE_SAVECT", CONTACT_TAB_DATABASE_SAVECT_ALL_ID = "CONTACT_TAB_DATABASE_SAVECT_ALL",
            CONTACT_TAB_DATABASE_IMPORT_ID = "CONTACT_TAB_DATABASE_IMPORT", CONTACT_TAB_DATABASE_DELCT_ID = "CONTACT_TAB_DATABASE_DELCT", CONTACT_TAB_DATABASE_DELCT_ALL_ID = "CONTACT_TAB_DATABASE_DELCT_ALL",
            JOP_OVERLIMIT_ID = "JOP_OVERLIMIT", JOP_CTC_ADD_ID = "JOP_CTC_ADD", JOP_CTC_ADD_EXISTS_ID = "JOP_CTC_ADD_EXISTS", JOP_CTC_ADDS_EXISTS_ID = "JOP_CTC_ADDS_EXISTS",
            JOP_CTC_ADDS_ID = "JOP_CTC_ADDS", JOP_CTC_DEL_ID = "JOP_CTC_DEL", JOP_CTC_DELALL_ID = "JOP_CTC_DELALL", JOP_CTC_SWAP_ID = "JOP_CTC_SWAP", JOP_CSV_IMPORT_ID = "JOP_CSV_IMPORT", 
            JOP_CSV_IMPORT_IOEX_ID = "JOP_CSV_IMPORT_IOEX", JOP_CSV_IMPORT_FNFEX_ID = "JOP_CSV_IMPORT_FNFEX", JOP_CSV_EXPORT_ID = "JOP_CSV_EXPORT", JOP_CSV_EXPORT_NO_CTC_ID = "JOP_CSV_EXPORT_NO_CTC" ,JOP_CSV_EXPORT_IOEX_ID = "JOP_CSV_EXPORT_IOEX", 
            JOP_ILLEGAL_CHAR_ID = "JOP_ILLEGAL_CHAR", JOP_CONF_EXPORT_ID = "JOP_CONF_EXPORT", JOP_CONF_EXPORT_IOEX_ID = "JOP_CONF_EXPORT_IOEX", JOP_CONF_IMPORT_ID = "JOP_CONF_IMPORT", 
            JOP_CONF_IMPORT_INVALID_ID = "JOP_CONF_IMPORT_INVALID", JOP_CONF_IMPORT_FNFEX_ID = "JOP_CONF_IMPORT_FNFEX", JOP_SINGLE_SMS_ID = "JOP_SINGLE_SMS", JOP_SETTING_TAB_INVALID_SENDER_ID = "JOP_SETTING_TAB_INVALID_SENDER",
            JOP_CONTACT_TAB_INVALID_CTC_ID = "JOP_CONTACT_TAB_INVALID_CTC", JOP_CONTACT_TAB_NO_SEL_ID = "JOP_CONTACT_TAB_NO_SEL", JOP_SETTING_TAB_COST_SET_ID = "JOP_SETTING_TAB_COST_SET", JOP_SETTING_TAB_INVALID_COST_ID = "JOP_SETTING_TAB_INVALID_COST",
            JOP_DATABASE_TEMPLATE_UNIQUE_ERR_ID = "JOP_DATABASE_TEMPLATE_UNIQUE_ERR", JOP_SMS_TAB_EMPITY_FIELD_ID = "JOP_SMS_TAB_EMPITY_FIELD", JOP_DATABASE_TEMPLATE_NO_SEL_ID = "JOP_DATABASE_TEMPLATE_NO_SEL",
            JOP_REQUEST_PERMISSION_ID = "JOP_REQUEST_PERMISSION", JOP_SMS_TAB_THREAD_ALIVE_ID = "JOP_SMS_TAB_THREAD_ALIVE", JOP_DATABASE_NO_CTDEL_FOUND_ID = "JOP_DATABASE_NO_DEL_FOUND", JOP_DATABASE_CT_ALL_DEL_ID = "JOP_DATABASE_CT_ALL_DEL",
            JOP_DATABASE_OVERRIDE_REQUEST_ID = "JOP_DATABASE_OVERRIDE_REQUEST", JOP_DATABASE_CT_DEL_ID = "JOP_DATABASE_CT_DEL", JOP_DATABASE_CT_DEL_INFO_ID = "JOP_DATABASE_CT_DEL_INFO", JOP_SMS_TAB_CODE200_ID = "JOP_SMS_TAB_CODE200", JOP_SMS_TAB_CODE401_ID = "JOP_SMS_TAB_CODE401", JOP_SMS_TAB_CODE402_ID = "JOP_SMS_TAB_CODE402",
            JOP_SMS_TAB_CODE500_ID = "JOP_SMS_TAB_CODE500", JOP_SMS_TAB_CODEIOE_IDS = "JOP_SMS_TAB_CODEIOE";
    
    private Localization()
    {
        Langs = new String[] {"english","italiano"};
        Locales = new HashMap<String,HashMap<String,String>>()
        {{
            put("italiano", new HashMap<>());
            put("english", new HashMap<>());
        }};
        populateLocale("english", Locales.get("english"));
        populateLocale("italiano", Locales.get("italiano"));
    }
    
    private void populateLocale(String lang, HashMap<String,String> locale)
    {
        switch(lang)
        {
            case "italiano":
                locale.put(SENDER_NOTE_ID, "<html>*In caso di numero invalido, verrà usata l'opzione predefinita</html>");
                locale.put(CURRENT_USER_ID,"Utente attuale:");
                locale.put(CONTACTS_ID,"Contatti:");
                locale.put(SMS_TAB_ID,"Invia un SMS");
                locale.put(SMS_TAB_SMS_SPACE_ID,"Scrivi qui il tuo SMS");
                locale.put(SMS_TAB_TEXT_LENGTH_ID,"L. del messaggio:");
		locale.put(SMS_TAB_NUM_SMS_ID,"SMS per Destinatario:");
		locale.put(SMS_TAB_OVERLIMIT_ID,"Supera il limite di lunghezza");
		locale.put(SMS_TAB_COST_ID,"Costo probabile:");
		locale.put(SMS_TAB_CUSTOM_NOTE_ID,"<html>Inserisci <font color=\"red\">%n (solo uno)</font> dovunque nel tuo messaggio. Sarà sostituito dal nome del contatto durante l'invio...</html>");
                locale.put(SMS_TAB_SENDING_ID, "Invio in corso...%s");
                locale.put(SMS_TAB_SEND_ID, "Invia SMS");
                locale.put(CONTACT_TAB_ID,"Rubrica");
                locale.put(SETTING_TAB_ID,"Impostazioni");
                locale.put(SETTING_TAB_USERNAME_ID,"Nome Utente:");
                locale.put(SETTING_TAB_SENDER_ID,"N. Telefonico Certificato*:");
                locale.put(SETTING_TAB_PASSWORD_ID,"Mostra Password");
                locale.put(SETTING_TAB_ACCOUNT_ID,"Imposta Account");
                locale.put(SETTING_TAB_CURRENT_API_ID,"API attuale:");
                locale.put(SETTING_TAB_NEW_API_ID,"Nuova API:");
                locale.put(SETTING_TAB_API_ID,"Utilizza questa API");
                locale.put(SETTING_TAB_COST_ID, "Costo SMS");
                locale.put(SETTING_TAB_COST_TEXT_ID, "Inserisci l'attuale costo per un SMS:");
                locale.put(SETTING_TAB_COST_CHECK_ID, "Controlla i costi");
                locale.put(SETTING_TAB_COST_SET_ID, "Imposta costo");
                locale.put(SETTING_TAB_RESPONSE_ID,"Formato risposta dell'API");
                locale.put(SETTING_TAB_REPORT_ID,"Conferma ricezione dell'SMS");
                locale.put(SETTING_TAB_REPORT_ENABLE_ID,"Segnala la ricezione del SMS (può essere controllato sul sito)");
                locale.put(SETTING_TAB_REPORT_DISABLE_ID,"Non segnalare la ricezione del SMS");
                locale.put(SETTING_TAB_SEND_ID,"Metodo di invio");
                locale.put(SETTING_TAB_SEND_GROUP_ID,"<html>Raggruppa i contatti e invia loro lo STESSO SMS con una singola richiesta POST</html>");
                locale.put(SETTING_TAB_SEND_SINGLE_ID,"<html>Esegui una richiesta per contatto, permettendo la personalizzazione con il nome del contatto</html>");
                locale.put(SETTING_TAB_LANGUAGE_ID,"Seleziona una lingua");
                locale.put(SETTING_TAB_SAVE_CONF_ID,"Salva configurazione");
                locale.put(SETTING_TAB_THEMES_ID,"Temi (creati da JTattoo):");
                locale.put(CONTACT_TAB_ADD_ID,"Aggiungi un contatto");
                locale.put(CONTACT_TAB_ADD_ACTION_ID,"Aggiungi alla rubrica");
                locale.put(CONTACT_TAB_CSV_ID,"Carica/Salva un CSV");
                locale.put(CONTACT_TAB_CSV_FORMAT_ID,"Seleziona il formato CSV:");
                locale.put(CONTACT_TAB_CSV_LOAD_ID,"Carica i contatti da CSV");
                locale.put(CONTACT_TAB_CSV_SAVE_ID,"Salva i contatti in un CSV");
                locale.put(CONTACT_TAB_MANAGE_ID,"Gestisci rubrica");
                locale.put(CONTACT_TAB_MANAGE_DEL_ID,"Elimina contatto selezionato");
                locale.put(CONTACT_TAB_MANAGE_DELALL_ID,"Elimina tutti i contatti");
                locale.put(CONTACT_TAB_MANAGE_SWAP_ID,"Scambia nome e cognome del contatto selezionato");
                locale.put(CONTACT_TAB_MANAGE_SWAPALL_ID,"Scambia nome e cognome di tutti i contatti");
                locale.put(CONTACT_TAB_TABLE_NAME_ID,"Nome");
                locale.put(CONTACT_TAB_TABLE_SURNAME_ID,"Cognome");
                locale.put(CONTACT_TAB_TABLE_PHONE_ID,"N. Telefonico");
                locale.put(CONTACT_TAB_DATABASE_SAVECT_ID,"Sal. contatto selezionato");
                locale.put(CONTACT_TAB_DATABASE_SAVECT_ALL_ID,"Sal. tutti i contatti");
                locale.put(CONTACT_TAB_DATABASE_IMPORT_ID,"Importa");
                locale.put(CONTACT_TAB_DATABASE_DELCT_ID,"Elim. contatto selezionato");
                locale.put(CONTACT_TAB_DATABASE_DELCT_ALL_ID,"Elim. tutti i contatti");
                locale.put(VIEWER_TITLE_ID, "A chi hai inviato questo SMS?");
                locale.put(VIEWER_SMS_TEXT_ID, "Messaggio:");
                locale.put(CHANGE_LANG_ID,"Lingua dell'interfaccia cambiata!");
                locale.put(CHANGE_ACCOUNT_ID,"Account di servizio cambiato!");
                locale.put(CHANGE_API_ID,"API di servizio cambiata!");
                locale.put(CHANGE_COST_ID,"Costo per singolo SMS cambiato!");
                locale.put(CHANGE_RESPONSE_ID,"Formato risposta API cambiato!");
                locale.put(CHANGE_THEME_ID,"Tema dell'interfaccia cambiato!");
                locale.put(JOP_OVERLIMIT_ID,"La lunghezza attuale del SMS supera il limite per il singolo SMS\nIl testo verrà troncato.\nVuoi continuare?");
                locale.put(JOP_CTC_ADD_ID,"%s %s è stato aggiunto correttamente!");
                locale.put(JOP_CTC_ADD_EXISTS_ID,"Il numero telefonico '%s' è stato già inserito!");
                locale.put(JOP_CTC_ADDS_EXISTS_ID,"I seguenti contatti sono già presenti in rubrica:\n%s");
                locale.put(JOP_CTC_ADDS_ID,"Aggiunti %d contatti");
                locale.put(JOP_CTC_DEL_ID,"%s %s (%s) eliminato correttamente");
                locale.put(JOP_CTC_DELALL_ID,"Tutti i contatti sono stati eliminati con successo");
                locale.put(JOP_CTC_SWAP_ID,"Inversione avvenuta con successo!");
                locale.put(JOP_CSV_IMPORT_ID,"Il file CSV è stato salvato correttamente!");
                locale.put(JOP_CSV_IMPORT_IOEX_ID,"Formato CSV invalido!\nSi è verificato un errore durante la lettura della riga: %d");
                locale.put(JOP_CSV_IMPORT_FNFEX_ID,"Nessun file CSV trovato");
                locale.put(JOP_CSV_EXPORT_ID,"Il file CSV è stato salvato correttamente!");
                locale.put(JOP_CSV_EXPORT_NO_CTC_ID,"Non c'è nessun contatto da salvare!");
                locale.put(JOP_CSV_EXPORT_IOEX_ID,"Si è verificato un errore durante il salvataggio del file csv");
                locale.put(JOP_ILLEGAL_CHAR_ID,"Il carattere '%c' non è supportato dalla codifica GSM7!");
                locale.put(JOP_CONF_EXPORT_ID,"Le impostazioni sono state salvate correttamente!");
                locale.put(JOP_CONF_EXPORT_IOEX_ID,"Si è verificato un errore durante il salvataggio delle impostazioni");
                locale.put(JOP_CONF_IMPORT_ID,"Le impostazioni sono state caricate correttamente!");
                locale.put(JOP_CONF_IMPORT_INVALID_ID,"Le seguenti impostazioni sono invalide:%s\nVerranno rimpiazzate da quelle predefinite");
                locale.put(JOP_CONF_IMPORT_FNFEX_ID,"Nessun file di configurazione trovato");
                locale.put(JOP_SINGLE_SMS_ID,"Hai raggiunto il limite di lunghezza per il singolo SMS!");
                locale.put(JOP_SETTING_TAB_INVALID_ACCOUNT_ID, "Credenziali non valide!");
                locale.put(JOP_SETTING_TAB_INVALID_API_ID,"L'url dell'API inserita non è valido");
                locale.put(JOP_SETTING_TAB_INVALID_SENDER_ID,"Il numero cerficiato è invalido (Lunghezza non valida)\nVerrà utilizzata l'opzione preferifina...");
                locale.put(JOP_SETTING_TAB_INVALID_COST_ID, "Valore non valido");
                locale.put(JOP_CONTACT_TAB_INVALID_CTC_ID, "I dati del contatto sono invalidi!");
                locale.put(JOP_CONTACT_TAB_NO_SEL_ID, "Seleziona un contatto!");
                locale.put(JOP_SETTING_TAB_COST_SET_ID, "Costo impostato a:");
                locale.put(JOP_W_TITLE_ID,"Avviso");
                locale.put(JOP_E_TITLE_ID,"Errore");
                locale.put(JOP_I_TITLE_ID,"Informazione");
                locale.put(JOP_DATABASE_TEMPLATE_UNIQUE_ERR_ID, "Il template che vuoi inserire è già presente nel database!");
                locale.put(JOP_SMS_TAB_EMPITY_FIELD_ID, "Inserisci almeno una parola nella casella di testo!");
                locale.put(JOP_DATABASE_TEMPLATE_NO_SEL_ID, "Seleziona un template!");
                locale.put(JOP_REQUEST_PERMISSION_ID, "Sei sicuro?");
                locale.put(DATABASE_ADD_TEMPLATE_ID, "Aggiungi il testo attuale come template");
                locale.put(DATABASE_LOAD_TEMPLATE_ID, "Importa il testo del template selezionato");
                locale.put(DATABASE_DELETE_TEMPLATE_ID, "Elimina il template selezionato");
                locale.put(DATABASE_DELETE_TEMPLATES_ID, "Elimina tutti i template");
                locale.put(DATABASE_SHOW_TEMPLATES_ID, "Visual. tutti i template");
                locale.put(DATABASE_DOUBLE_CLICK_ID, "Clicca due volte su una riga per visualizzarla meglio!");
                locale.put(DATABASE_ADD_CONTACT_ID,"%s %s è stato aggiunto correttamente al database!");
                locale.put(DATABASE_NO_OVERRIDE_CONTACT_ID, "%s %s non è stato sovrascritto nel database!");
                locale.put(DATABASE_OVERRIDE_CONTACT_ID, "%s %s è stato sovrascritto nel database!");
                locale.put(DATABASE_SHOW_ALL_SMS_SENT_ID, "Mostra tutti gli SMS inviati");
                locale.put(JOP_DATABASE_OVERRIDE_REQUEST_ID, "Il numero %s è attualmente inserito come\n %s %s\n Vuoi sovrascriverlo con\n%s %s ?");
                locale.put(JOP_DATABASE_NO_CTDEL_FOUND_ID, "Il contatto selezionato non è nel database!");
                locale.put(JOP_DATABASE_CT_DEL_ID, "%s è stato rimosso correttamente al database!");
                locale.put(JOP_DATABASE_CT_ALL_DEL_ID, "Contatti del database svuotati!");
                locale.put(JOP_DATABASE_CT_DEL_INFO_ID, "Sei sicuro?\nVerranno eliminate anche le informazioni nel DB sui messaggi ricevuti dal contatto!");
                locale.put(JOP_SMS_TAB_THREAD_ALIVE_ID,"Un processo di invio è già in esecuzione!");
                locale.put(JOP_SMS_TAB_CODE200_ID,"Gli SMS sono stati inviati correttamente!");
                locale.put(JOP_SMS_TAB_CODE401_ID,"L'utente non è valido per accedere al servizio!");
                locale.put(JOP_SMS_TAB_CODE402_ID,"Credito insufficiente!");
                locale.put(JOP_SMS_TAB_CODE500_ID,"Errore sconosciuto, possibili cause:\nNessun testo inserito\nNumero destinatario non valido\nSconosciuto");
                
                break;
           default: //english
                locale.put(SENDER_NOTE_ID, "<html>*If the number is invalid, the default parameter will be used</html>");
                locale.put(CURRENT_USER_ID,"Current User:");
                locale.put(CONTACTS_ID,"Contacts:");
                locale.put(SMS_TAB_ID,"Send a SMS");
		locale.put(SMS_TAB_SMS_SPACE_ID,"Write here your SMS");
		locale.put(SMS_TAB_TEXT_LENGTH_ID,"Length of the text:");
		locale.put(SMS_TAB_NUM_SMS_ID,"SMS per Receiver:");
		locale.put(SMS_TAB_OVERLIMIT_ID,"Exceed the length limit");
		locale.put(SMS_TAB_COST_ID,"Expected cost:");
		locale.put(SMS_TAB_CUSTOM_NOTE_ID,"<html>Insert <font color=\"red\">%n (only one allowed)</font> anywhere in your SMS text. It will be replaced with the Contact's name while sending...</html>");
                locale.put(SMS_TAB_SENDING_ID, "Sending...%s");
                locale.put(SMS_TAB_SEND_ID, "Send SMS");
                locale.put(CONTACT_TAB_ID,"Contact Library");
                locale.put(SETTING_TAB_ID,"Settings");
                locale.put(SETTING_TAB_USERNAME_ID,"Username:");
                locale.put(SETTING_TAB_SENDER_ID,"Certified Phone Number*:");
                locale.put(SETTING_TAB_PASSWORD_ID,"Show Password");
                locale.put(SETTING_TAB_ACCOUNT_ID,"Set Account");
                locale.put(SETTING_TAB_CURRENT_API_ID,"Current API:");
                locale.put(SETTING_TAB_NEW_API_ID,"New API:");
                locale.put(SETTING_TAB_API_ID,"Use this API");
                locale.put(SETTING_TAB_COST_ID, "SMS cost");
                locale.put(SETTING_TAB_COST_TEXT_ID, "Enter the current cost for one SMS:");
                locale.put(SETTING_TAB_COST_CHECK_ID, "Check costs");
                locale.put(SETTING_TAB_COST_SET_ID, "Set cost");
                locale.put(SETTING_TAB_RESPONSE_ID,"API response format");
                locale.put(SETTING_TAB_REPORT_ID,"Confirm SMS arrival");
                locale.put(SETTING_TAB_REPORT_ENABLE_ID,"Report SMS arrival (It can be checked via web)");
                locale.put(SETTING_TAB_REPORT_DISABLE_ID,"Don't report SMS arrival");
                locale.put(SETTING_TAB_SEND_ID,"Send method");
                locale.put(SETTING_TAB_SEND_GROUP_ID,"<html>Group up contacts and send them the SAME SMS with a single POST request</html>");
                locale.put(SETTING_TAB_SEND_SINGLE_ID,"<html>Do a request for each contact, allowing SMS customization with contact's name</html>");
                locale.put(SETTING_TAB_LANGUAGE_ID,"Select a language");
                locale.put(SETTING_TAB_SAVE_CONF_ID,"Save configurarion");
                locale.put(SETTING_TAB_THEMES_ID,"Themes (by JTattoo):");
                locale.put(CONTACT_TAB_ADD_ID,"Add a contact");
                locale.put(CONTACT_TAB_ADD_ACTION_ID,"Add to the library");
                locale.put(CONTACT_TAB_CSV_ID,"Load/Save a CSV");
                locale.put(CONTACT_TAB_CSV_FORMAT_ID,"Select your CSV format:");
                locale.put(CONTACT_TAB_CSV_LOAD_ID,"Load contacts from CSV");
                locale.put(CONTACT_TAB_CSV_SAVE_ID,"Save contacts in a CSV");
                locale.put(CONTACT_TAB_MANAGE_ID,"Manage library");
                locale.put(CONTACT_TAB_MANAGE_DEL_ID,"Delete selected contact");
                locale.put(CONTACT_TAB_MANAGE_DELALL_ID,"Delete all contacts");
                locale.put(CONTACT_TAB_MANAGE_SWAP_ID,"Swap first name and last name of the selected contact");
                locale.put(CONTACT_TAB_MANAGE_SWAPALL_ID,"Swap first name and last name of all contacts");
                locale.put(CONTACT_TAB_TABLE_NAME_ID,"First Name");
                locale.put(CONTACT_TAB_TABLE_SURNAME_ID,"Last Name");
                locale.put(CONTACT_TAB_TABLE_PHONE_ID,"Phone Number");
                locale.put(CONTACT_TAB_DATABASE_SAVECT_ID,"Save selected contact");
                locale.put(CONTACT_TAB_DATABASE_SAVECT_ALL_ID,"Save all contacts");
                locale.put(CONTACT_TAB_DATABASE_IMPORT_ID,"Import");
                locale.put(CONTACT_TAB_DATABASE_DELCT_ID,"Delete selected contact");
                locale.put(CONTACT_TAB_DATABASE_DELCT_ALL_ID,"Delete all contacts");
                locale.put(VIEWER_TITLE_ID, "Who have you sent this SMS?");
                locale.put(VIEWER_SMS_TEXT_ID, "Messagge:");
                locale.put(CHANGE_LANG_ID,"Interface language changed!");
                locale.put(CHANGE_ACCOUNT_ID,"Service account changed!");
                locale.put(CHANGE_API_ID,"Service API changed!");
                locale.put(CHANGE_COST_ID,"Single SMS cost changed!");
                locale.put(CHANGE_RESPONSE_ID,"API response format changed!");
                locale.put(CHANGE_THEME_ID,"Interface theme changed!");
                locale.put(JOP_OVERLIMIT_ID,"Current SMS text length exceeds single SMS limit.\nText will be cut.\nAre you sure?");
                locale.put(JOP_CTC_ADD_ID,"%s %s has been added!");
                locale.put(JOP_CTC_ADD_EXISTS_ID,"Phone number '%s' is already inserted!");
                locale.put(JOP_CTC_ADDS_EXISTS_ID,"The following contacts already exist:\n%s");
                locale.put(JOP_CTC_ADDS_ID,"%d contacts have been added!");
                locale.put(JOP_CTC_DEL_ID,"%s %s (%s) successfully deleted");
                locale.put(JOP_CTC_DELALL_ID,"All contacts have been successfully deleted");
                locale.put(JOP_CTC_SWAP_ID,"Successfully swapped!");
                locale.put(JOP_CSV_IMPORT_ID,"CSV file successfully imported!");
                locale.put(JOP_CSV_IMPORT_IOEX_ID,"Invalid CSV format!\nAn error occurred while reading line: %d");
                locale.put(JOP_CSV_IMPORT_FNFEX_ID,"No CSV file has been found");
                locale.put(JOP_CSV_EXPORT_ID,"CSV file successfully saved!");
                locale.put(JOP_CSV_EXPORT_NO_CTC_ID,"No contacts to be saved!");
                locale.put(JOP_CSV_EXPORT_IOEX_ID,"An error occurred while saving csv file.");
                locale.put(JOP_ILLEGAL_CHAR_ID,"'%c' is not a GSM7 supported character!");
                locale.put(JOP_CONF_EXPORT_ID,"Configuration successfully saved!");
                locale.put(JOP_CONF_EXPORT_IOEX_ID,"A error occurred while saving configuration");
                locale.put(JOP_CONF_IMPORT_ID,"Configuration successfully imported!");
                locale.put(JOP_CONF_IMPORT_INVALID_ID,"The following props are invalid:%s\nThese will be replaced with default configuration");
                locale.put(JOP_CONF_IMPORT_FNFEX_ID,"No configuration file has been found");
                locale.put(JOP_SINGLE_SMS_ID,"You have reached the length limit for single SMS!");
                locale.put(JOP_SETTING_TAB_INVALID_ACCOUNT_ID, "Invalid Credentials!");
                locale.put(JOP_SETTING_TAB_INVALID_API_ID,"API url is invalid");
                locale.put(JOP_SETTING_TAB_INVALID_SENDER_ID,"Invalid Certified Phone Number (Illegal length)\nUsing default setting...");
                locale.put(JOP_CONTACT_TAB_INVALID_CTC_ID, "Invalid contact data!");
                locale.put(JOP_CONTACT_TAB_NO_SEL_ID, "Select a contact!");
                locale.put(JOP_SETTING_TAB_COST_SET_ID, "Cost set to:");
                locale.put(JOP_SETTING_TAB_INVALID_COST_ID, "Invalid value");
                locale.put(JOP_W_TITLE_ID,"Warning");
                locale.put(JOP_E_TITLE_ID,"Error");
                locale.put(JOP_I_TITLE_ID,"Information");
                locale.put(JOP_DATABASE_TEMPLATE_UNIQUE_ERR_ID, "The template that you want to add is already inside the database!");
                locale.put(JOP_SMS_TAB_EMPITY_FIELD_ID, "Enter at least one word on the textfield!");
                locale.put(JOP_DATABASE_TEMPLATE_NO_SEL_ID, "Select a template!");
                locale.put(JOP_REQUEST_PERMISSION_ID, "Are you sure?");
                locale.put(DATABASE_ADD_TEMPLATE_ID, "Add current text as template");
                locale.put(DATABASE_LOAD_TEMPLATE_ID, "Load selected template as text");
                locale.put(DATABASE_DELETE_TEMPLATE_ID, "Delete selected template");
                locale.put(DATABASE_DELETE_TEMPLATES_ID, "Delete all templates");
                locale.put(DATABASE_SHOW_TEMPLATES_ID, "Show all templates");
                locale.put(DATABASE_DOUBLE_CLICK_ID, "Double-click on a row to get a summary!");
                locale.put(DATABASE_ADD_CONTACT_ID,"%s %s has been added to the database!");
                locale.put(DATABASE_NO_OVERRIDE_CONTACT_ID, "%s %s has been not overwritten on the database!");
                locale.put(DATABASE_OVERRIDE_CONTACT_ID, "%s %s has been overwritten with %s %s on the database!");
                locale.put(DATABASE_SHOW_ALL_SMS_SENT_ID, "Show all sent SMS");
                locale.put(JOP_DATABASE_OVERRIDE_REQUEST_ID, "The phone-number %s is actually registered as\n %s %s\n Do you want to overwrite with\n%s %s ?");
                locale.put(JOP_DATABASE_NO_CTDEL_FOUND_ID, "The selected contact doesn't exists inside the database!");
                locale.put(JOP_DATABASE_CT_DEL_ID,"%s has been removed from the database!");
                locale.put(JOP_DATABASE_CT_DEL_INFO_ID, "Are you sure?\nAlso DB information about contact's sms recieved will be removed!");
                locale.put(JOP_DATABASE_CT_ALL_DEL_ID, "Database Contacts cleaned!");
                locale.put(JOP_SMS_TAB_THREAD_ALIVE_ID,"A sending process is already running!");
                locale.put(JOP_SMS_TAB_CODE200_ID,"SMS has been sent successfully!");
                locale.put(JOP_SMS_TAB_CODE401_ID,"Account isn't valid. Can't use the service!");
                locale.put(JOP_SMS_TAB_CODE402_ID,"No enough credit to send SMS!");
                locale.put(JOP_SMS_TAB_CODE500_ID,"Unknown Error, presumable reasons:\nNo text has been typed\nDestination Number is invalid\nUnknown");
                break;
        }
    }
    
    public String getText(String ID)
    {
        return Locales.get(Configuration.getInstance().getLanguage()).get(ID);
    }
    
    public static Localization getInstance()
    {
        return instance;
    }
    
    public boolean isLocale(String language)
    {
        for(String l : Langs)
        {
           if(l.toLowerCase().equals(language.toLowerCase())) 
           {
               return true;
           }
        }
        return false;
    }
    
    public String[] getLangsList()
    {
        return Langs;
    }
}
