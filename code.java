# Lasse9 Dev Mobile

# Activities:

## Creation:

**Méthode Declarative:**

```xml
<?xml version = "1.0" encoding = "utf-8"?>
<LinearLayout
xmlns:android = "http://schemas.android.com/apk/res/android"
xmlns:tools = "http://schemas.android.com/tools"
android:layout_width = "match_parent"
android:layout_height = "match_parent"
tools:context = "ma.ensias.authentification.AccueilActivity">
 <TextView
android:layout_width = "wrap_content"
        android:layout_height = "wrap_content"/>
</LinerLayout>
```

**Méthode Impérative:**

```java
public class MyActivity extends Activity {
    protected void onCreate(Bundle monBundle) {
        super.onCreate(monBundle);
        LinearLayout monLinearLayout = new LinearLayout(this);
        TextView tvMonTexte = new TextView(this);
        monLinearLayout.addView(tvMonTexte);
        setContentView(monLinearLayout);
        monTexteView.setText("Bonjour tout le monde");
    }
}
```

## Associer l’interface à une activité:

```java
public class MyActivity extends Activity {
    protected void onCreate(Bundle monBundle) {
        super.onCreate(monBundle);
        setContentView(R.layout.content_layout_id);
        final TextView tvMonTexte  = (TextView) findViewById(R.id.monTexte);
        tvMonTexte.setText("Bonjour tout le monde");
    }
}
```

## Création de vue sans définition XML:

```java
public class MyActivity extends Activity {
    protected void onCreate(Bundle monBundle) {
        super.onCreate(monBundle);
        LinearLayout monLinearLayout = new LinearLayout(this);
        monLinearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView tvMonTexte  = new TextView(this);
        monLinearLayout.addView(tvMonTexte);
        setContentView(monLinearLayout);
        tvMonTexte.setText("Bonjour tout le monde");
    }
}

public class MyActivity extends Activity {
    protected void onCreate(Bundle monBundle) {
        super.onCreate(monBundle);
        TextView tvMonTexte  = new TextView(this);
        setContentView(tvMonTexte);
        tvMonTexte.setText("Bonjour tout le monde");
    }
}
```

## Associer un listener d’événement:

**Via le fichier XML:**

```xml
<Button
android:id = "@+id/monBouton" android:layout_width = "wrap_content" android:layout_height = "wrap_content" android:text = "cliquer" android:onClick = "afficherMsg" tools:layout_editor_absoluteX = "141dp" tools:layout_editor_absoluteY = "105dp"
/>
```

```java
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void afficherMsg(View view) {
        Toast.makeText(getApplicationContext(), "Vous avez cliqué sur le bouton", Toast.LENGTH_SHORT).show();
    }
}
```

**Via le code Java:**

```xml
<Button
android:id = "@+id/monBouton" android:layout_width = "wrap_content" android:layout_height = "wrap_content" android:text = "cliquer" tools:layout_editor_absoluteX = "141dp" tools:layout_editor_absoluteY = "105dp"
/>
```

```java
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        Button leBouton = findViewById(R.id.monBouton);
        leBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Vous avez cliquez sur le bouton", Toast.LENGTH_LONG).show();
            }
        });
    }
}
```

**Via le code Java en donnant comme hundler l’activité (this):**

```xml
<Button
android:id = "@+id/monBouton" android:layout_width = "wrap_content" android:layout_height = "wrap_content" android:text = "cliquer" tools:layout_editor_absoluteX = "141dp" tools:layout_editor_absoluteY = "105dp"
/>
```

```java
public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        Button leBouton = (Button) this.findViewById(R.id.monBouton); leBouton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Vous avez cliquez sur le bouton", Toast.LENGTH_LONG).show();
    }
}
```

## Affichage des messages d’info:

```java
// Soit celui-là
Toast.makeText(getApplicationContext(), "Mon message", Toast.LENGTH_SHORT).show();

// Ou celui-là
Toast.makeText(getApplicationContext(), R.string.idRessource, Toast.LENGTH_SHORT).show();
```

# Intent:

## Démarrer une activité sans retour:

**Utilisation de la méthode startActivity de la classe Activity**

```java
Intent intent  =  new Intent(this, ActiviteADemarrer.class);
startActivity(intent);
```

**La déclaration d’une activité dans le Manifest**

```xml
<application .....>
    <activity android:name = ".MonActivite"/>
</application>
```

## Démarrer une activité avec retour:

**La méthode startActivityForResult permet d’avertir l’activité parente une fois l’activité fille a terminé et de communiquer une valeur de retour**

```java
Static final int CODE_ACTIVITE_ENFANT = 1;
Intent intent  =  new Intent(this, ActiviteADemarrer.class); startActivityForResult(intent, CODE_ACTIVITE_ENFANT);
```

## Appel d’une application:

**La syntaxe générale du lancement d’une activité :**

```java
Uri monUri  = Uri.parse("schéma://hote:port/chemin");
Intent intent  =  new Intent(Intent.NOM_ACTION, monUri);
startActivity(intent);
```

## Les permissions:

```xml
<manifest ......>
    <application>
    ...
    </application>
    <uses-permission android:name = "android.permission.READ_CONTACTS"/>
    <uses-permission android:name = "android.permission.CALL_PHONE"/>
</manifest>
```

## Configuration d’un filtre:

```xml
<application
android:allowBackup = "true"
android:icon = "@mipmap/ic_launcher"
android:label = "@string/app_name"
android:supportsRtl = "true"
android:theme = "@style/AppTheme">
    <activity android:name = ".TestFiltreActivity">
        <intent-filter>
            <action android:name = "android.intent.action.VIEW" />
            <category android:name = "android.intent.category.DEFAULT" />
            <category android:name = "android.intent.category.BROWSABLE" />
            <data android:scheme = "demo"/>
        </intent-filter>
    </activity>
</application>
```

## Traitement de l’objet Intent:

**On peut récupérer les data de l’Intent sous forme de String**

```java
protected void onCreate(Bundle monBundle) {
    super.onCreate(monBundle);
    String data = getIntent().getDataString();
    if(data! = null){
        //on peut traiter les données comme on peut retransmettre //l’Intent à une autre activité
        startNextMatchingActivity(getIntent());
    }
}
```

**Il est possible de récupérer la donnée sous forme d’un objet Uri tel qu’il a été envoyé initialement**

```java
protected void onCreate(Bundle monBundle) {
    super.onCreate(monBundle);
    Uri data = getIntent().getData();
    if(data! = null){
        //le code à exécuter traitant les data de l’Intent
    }
}
```

## Ajout de données supplémentaire:

**L’insertion des données dans ce conteneur**

```java
Intent intent  =  new Intent(this, ActiviteADemarrer.class);
intent.putExtra("maCle", edtNom.getText().toString());
startActivity(intent);
```

**La récupération des données à partir de l’objet Bundle, une fois l’objet récupéré, on utilise getXXX pour la récupération des valeurs transmises**

```java
protected void onCreate(Bundle monBundle) {
    super.onCreate(monBundle);
    Bundle extra = this.getIntent().getExtras();
    if(extra! = null){
        String monMsg = extra.getString("maCle");
    }
}
```

## Récepteur d’intention:

**La déclaration d’un récepteur d’intention se fait au niveau du fichier AndroidManifest.xml via la tag receiver :**

```xml
<receiver android:name = ".MyBroadcastReceiver"/>
```

## Durée de vie du récepteur d’intention:

- On ne déclare pas le récepteur dans AndroidManifest. xml
- On appelle la méthode registerReceiver() dans la méthode onResume() de l’activité pour annoncer son intérêt pour une intention,
- Au niveau de onPause(), on appelle la méthode unregisterReceiver() lorsqu’on n’a plus besoin de ces intentions.

```java
protected void onResume() {
    super.onResume();
    myBroadcastReceiver  = new MyBroadcastReceiver();
    registerReceiver(myBroadcastReceiver, new IntentFilter(ACTION_FILTER));
}

protected void onPause() {
    super.onPause();
    unregisterReceiver(myBroadcastReceiver);
}
```

# SQLite:

## SQLiteDataBase

```java
SQLiteDatabase myBD =  oHelper.getWritableDatabase();

SQLiteDatabase myBD =  oHelper.getReadableDatabase();
```

## Les étapes d’accès:

### Pour simplifier la création de la BdD SDK Android propose la classe d’aide SQLiteOpenHelper :

- Il faut créer une classe dérivée de SQLiteOpenHelper
- Redéfinir le constructeur, les méthodes onCreate (pour la création de la BdD) et onUpgrade (invoquée par le système automatiquement à chaque mise à jour dans la structure)

### N.B :

- C'est recommandé de créer une classe par table et de définir les méthodes onCreate() et onUpgrade(), qui sont appelées dans les méthodes correspondantes de la superclasse SQLiteOpenHelper.
- Les tables de la BdD doivent avoir l’identifiant \_id comme clé primaire de la table. Plusieurs fonctions Android s'appuient sur cette norme.

### Création d’un adaptateur où on déclare :

- Version et le nom de la BdD,
- Le nom de la table à manipuler
- Une instance de la BdD (SQLiteDatabase)
- Une instance de la classe héritant de SQLiteOpenHelper
- Le constructeur initialisant l’instance de la classe héritant de SQLiteOpenHelper et ayant comme arguments le contexte, nom et la version la BdD(lors de cette création si la BdD n’est pas créée elle le sera)
- Méthode ouvrant la BdD

## Représentation Java de la base de données SQLite:

```java
ContentValues vColonne = new ContentValues();
vColonne.put(nomColonne, valColonne);
```

## Methode Query:

**Code Java:**

```java
Cursor sqlitedatabase.query(String nomTable, String[] nomsColonnes, String clauseWhere, String[] argsSelection, String[] groupBy, String[] having, String[] orderBy)
```

## Methode Cursor:

**Code Java:**

```java
while (cPersonnes.moveToNext()) {
    // Faire quelque chose
}
cPersonnes.close();

for(cPersonnes.moveToFirst(); !cPersonnes.isAfterLast(); cPersonnes.moveToNext()) {
    // Faire quelque chose
}
cPetrsonnes.close();
```

## Les événements ArrayAdapter:

**Code Java:**

```java
onItemClick(AdapterView<?> adapterView, View view, int index, long l);
```

## Utilisation de ListActivity:

**Fichier XML:**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent" android:layout_height="match_parent" android:orientation="vertical"
>
    <ListView
    android:id="@android:id/list" android:layout_width="match_parent" android:layout_height="match_parent"
    />
</LinearLayout>
```

**Code Java:**

```java
public class MyAdapter2 extends ListActivity {
    ListView listeContacts;
    String[] data = new String[]{
        "Bouslamti",
        "Mouadi",
        "Benali",
        "Rifai",
        "Majdoubi",
        "Boumalek",
        "Karimi"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_adapter2);
        ArrayAdapter<String> myAdapter= new ArrayAdapter<String> (MyAdapter2.this,
        android.R.layout.simple_list_item_1, data);
        setListAdapter(myAdapter);
    }
}
```

## Utilisation de Activity:

**Fichier XML:**

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent" android:layout_height="match_parent"
>
    <ListView
    android:id="@+id/lstContacts" android:layout_width="match_parent" android:layout_height="wrap_content"
    />
</RelativeLayout>
```

**Code Java:**

```java
public class MyAdapter extends Activity{
    ListView listeContacts;
    String[] data = new String[]{
        "Bouslamti",
        "Mouadi",
        "Benali",
        "Rifai",
        "Majdoubi",
        "Boumalek",
        "Karimi"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_adapter);
        listeContacts = (ListView)findViewById(R.id.lstContacts);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String> (this,
        android.R.layout.simple_list_item_1, data);
        listeContacts.setAdapter(myAdapter);
    }
}
```

**Fichier XML:**

```xml

```

**Code Java:**

```java

```

# Fournisseurs de contenu:

## ContentResolver:

```java
ContentResolver contentResolver =  getContentResolver()
```

## La méthode query:

```java
ContentResolver contentResolver =  getContentResolver();
contentResolver.query(uri,projection, where, argsWhere, order);
```

## Lecture des données:

```java
//Récupérer toutes données du fournisseur
Cursor monCurseur =  getContentResolver().query(NomFournisseur.CONTENT_URI, null, null, null, null);

//Filtrer des données retournées et trier par ordre croissant
String[] projection = new String[]{"_id", "nom", "prenom","tel"}; String where = "nom = ? And prenom = ?";
String argsWhere = new String[]{"Benali", "Yasser"};
String ordre = "nom ASC";
Cursor monCurseur  =  getContentResolver().query(NomFournisseur.CONTENT_URI, projection, where, argsWhere, ordre);
```

## La méthode managedQuery:

```java
Cursor leCurseur  = managedQuery(uri, projection, where, argsWhere, ordre);

//pour restreindre la requête à un élément en particulier
Uri uri = Uri.parse("content://ma.ensias.professeurs/professeurs"); Uri profID10 =  ContentUris.withAppendedId(uri, 10);
Cursor monCurseur = managedQuery(profID10, null, null, null, null);

//pour restreindre la requête à un élément en particulier
Uri uri = Uri.parse("content://ma.ensias.professeurs/professeurs"); Uri profID10 =  ContentUris.withAppendedPath(uri, "10");
Cursor monCurseur = managedQuery(profID10, null, null, null, null);

//pour restreindre la requête à un élément en particulier
Uri profID10 = Uri.parse("content://ma.ensias.professeurs/professeurs/10"); Cursor monCurseur = managedQuery(profID10, null, null, null, null);
```

## Fournisseur de contacts:

```java
public class ListeContacts extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contacts);
        Cursor cContacts  =  getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,
        null, null, null);
        while (cContacts.moveToNext()) {
            String contactId  =  cContacts.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            int nomContactsIndex  =  cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
            String nomContact  =  cursor.getString(nomContactsIndex);
            int ayantTelIndex = cContact.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            String ayantTel  =  cursor.getString(ayantTelIndex);
            if (ayantTel.equals("1")) {
                Cursor cPhones  =  getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "  =  " + contactId, null, null);
                while (cPhones.moveToNext()) {
                    int numTelIndex = cPhones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER); String numTel  =  cPhones.getString(numTelIndex);
                    Log.i("", nomContact +"-----"+ numTel );
                }
                cPhones.close();
            }
        }
        cContacts.close();
    }
}
```

```xml
<manifest ......>
    <uses-permission android:name = "android.permission READ_CONTACTS"/>
    <uses-permission android:name = "android.permission.WRITE_CONTACTS"/>
</manifest>
```

## Insertion des données:

```java
public final Uri insert (Uri url, ContentValues values);

// Plusieurs valeurs
public final Uri bulkInsert (Uri url, ContentValues[] values);

//pour restreindre la requête à un élément en particulier ContentResolver cr = getContentResolver();
Uri uriENSIAS = Uri.parse("content://ma.ensias.professeurs/professeurs");
ContentValues ligne = new ContentValues();
ligne.put("nom", "Bouslamti");
ligne.put("prenom", "Ahmed");
ligne.put("eMail", "a.bouslamti@ensias.ma");
Uri monUri = cr.insert(uriENSIAS, ligne);
```

## Suppression des données:

```java
public final int delete (Uri url, String where, String[] argsWhere)

//pour restreindre la requête à un élément en particulier ContentResolver cr=getContentResolver();
Uri uriENSIAS = Uri.parse("content://ma.ensias.professeurs/professeurs");
String where = "nom=? AND prenom=?";
String[] args = new String[]{"Bouslamti", "Ahmed"};
cr.delete(uriENSIAS, where, args);
//Pour vider tout le contenu de la source Cr.delete’uriENSIAS, null,null);
```

## Mise à jour des données:

```java
public final int update (Uri uri, ContentValues values, String where, String[] argsWhere)

//pour restreindre la requête à un élément en particulier ContentResolver cr=getContentResolver();
Uri uriENSIAS = Uri.parse("content://ma.ensias.professeurs/professeurs");
ContentValues ligne = new ContentValues();
ligne.put("nom", "Bouslamti");
ligne.put("prenom", "Ali");
String where = "nom=? AND prenom=?";
String[] args = new String[]{"Bouslamti", "Ahmed"}; cr.update(uriENSIAS, ligne, where, args);
```

# Accès réseau et Services Web:

## Statut de l’appareil:

```xml
<uses-permission android:name="android.permission.CALL_PHONE"/>
```

## Téléphonie:

**Permissions nécessaires:**

```xml
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>

<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```

**Code Java:**

```java
void listen (PhoneStateListener listener, int events)

// Exemple
public class MainActivity extends AppCompatActivity {
    TextView txtView;
    String txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView)findViewById(R.id.txtView);
        TelephonyManager telMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        switch (telMgr.getPhoneType()){
            case TelephonyManager.PHONE_TYPE_NONE:
                txt = "Le type de téléphone est indéfini";
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                txt = "Le télephone est de type GSM";
                break;
            case TelephonyManager.PHONE_TYPE_CDMA:
                txt = "Le téléphone est de type CDMA";
                break;
        }
        txtView.setText(txt);
    }
}
```

## Disponibilité du réseau :

**Permissions nécessaires:**

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```

**Code Java:**

```java
public class MainActivity extends AppCompatActivity {
    TextView txtView;
    String txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView)findViewById(R.id.txtView);
        ConnectivityManager netMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfos = netMgr.getActiveNetworkInfo();
        switch (netInfos.getState()){
            case DISCONNECTED:
                txt="le téléphone est connecté";
                break;
            case CONNECTED:
                txt="Etat du télephone : Connecté ";
                break;
            case DISCONNECTING:
                txt="Etat du télephone : Déconnexion";
                break;
            case CONNECTING:
                txt="Etat du télephone : Encours de connexion";
                break;
        }
        txt += "\n Identifiant du type de réseau : " + netInfos.getType();
        txt += "\n Nom du type de réseau : " + netInfos.getTypeName();
        txt += "\n Detail sur le réseau : " + netInfos.getDetailedState().name();
        txt += "\n Extra info : " + netInfos.getExtraInfo();
        txtView.setText(txt);
    }
}
```

## Fractionner un URL:

**Code Java:**

```java
    public class USEUrlMethode {
        public static void main (String args[]){
            URL URLWeb=null;
            try {
                URLWeb=new URL("http://ensias.um5.ac.ma/page/formation-ing%C3%A9nieur-0");
                System.out.println("URL est : " + URLWeb);
                System.out.println("Le protocole : " + URLWeb.getProtocol());
                System.out.println("Le nom du Hote : " + URLWeb.getHost());
                System.out.println("Le numéro du port : " + URLWeb.getPort());
                System.out.println("Le chemin d'accès et nom du fichier:"+ URLWeb.getFile());
                System.out.println("L'ancre : " + URLWeb.getRef());
            } catch(MalformedURLException e){
                System.err.println(URLWeb + " impossible de traiter cet URL");
            }
        }
    }
```

## Extraction des données à partir d’un URL:

```java
public class Test {
    public static void main(String[] args) {
        String ligne;
        try {
            URL myURL=new URL("http://www.ensias.ma");
            InputStream in = myURL.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while((ligne=br.readLine())!=null){
                System.out.println(ligne);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## Lecture d’une page Web:

**Permissions:**

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

**Code Java:**

```java
StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
StrictMode.setThreadPolicy(policy);

// Exemple
public class MainActivity extends AppCompatActivity {
    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView=(WebView) findViewById(R.id.webView);
        //StrictMode.enableDefaults();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL monURL = null;
        try {
            monURL = new URL("http://ensias.um5.ac.ma");
            HttpURLConnection httpUrlCx = (HttpURLConnection) monURL.openConnection();
            InputStream in = httpUrlCx.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder data=new StringBuilder();
            String ligne;
            while ((ligne=br.readLine())!=null){
                data.append(ligne);
                webView.loadData(data.toString(), "text/html", "UTF-8"); httpUrlCx.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## API Jackson JSON Parser:

**Code Java:**

```java
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
public class TestJackson {
    public static void main(String[] args) {
        List<Contact> lstContacts = new ArrayList<>();
        List<Contact> lstContactsCopie = new ArrayList<>();
        List <String> emails0 = new ArrayList<String>(){
            {
                add("niri@yahoo.fr");
                add("y.niri@gmail.com");
            }
        };
        List <String> emails1=new ArrayList<String>(){
            {
                add("y.radi@yahoo.com");
                add("ali.radi@gmail.com");
            }
        };
        List <String> emails3=new ArrayList<String>(){
            {
                add("elkamel@gmail.com");
            }
        };
        lstContacts.add(new Contact(0, "Niri",emails0));
        lstContacts.add(new Contact(1, "Radi",emails1));
        lstContacts.add(new Contact(2, "Elkamel",emails3));
        ObjectMapper oMapper=new ObjectMapper();
        oMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String fluxJson;
        try {
            fluxJson=oMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lstContacts);
            System.out.println(fluxJson);
            System.out.println("---------------------------------------------------------------");
            TypeReference contactTypeList=new TypeReference<List<Contact>>() {};
            lstContactsCopie=oMapper.readValue(fluxJson, contactTypeList);
            for(Contact c:lstContactsCopie){
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## API Gson de Google:

**Code Java:**

```java
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
public class TestGson {
    public static void main(String[] args) {
        List<Contact> lstContacts=new ArrayList<>();
        List<Contact> lstContactsCopie=new ArrayList<>();
        List <String> emails0=new ArrayList<String>(){
            {
                add("niri@yahoo.fr");
                add("a.niri@gmail.com");
            }
        };
        List <String> emails1=new ArrayList<String>(){
            {
                add("y.radi@yahoo.com");
                add("yasser.radi@gmail.com");
            }
        };
        List <String> emails3=new ArrayList<String>(){
            {
                add("elkamel@gmail.com");
            }
        };
        lstContacts.add(new Contact(0, "Niri",emails0));
        lstContacts.add(new Contact(1, "Radi",emails1));
        lstContacts.add(new Contact(2, "Elkamel",emails3));
        Gson gson=new GsonBuilder().create();
        String fluxJson=gson.toJson(lstContacts);
        System.out.println(fluxJson);
        System.out.println("-------------------------------------------------------------");
        Type contactTypeList = new TypeToken<ArrayList<Contact>>(){}.getType();
        lstContactsCopie=gson.fromJson(fluxJson,contactTypeList);
        for(Contact c:lstContactsCopie){
            System.out.println(c);
        }
    }
}
```

## Web service via l’API Jersey:

**Code Java:**

```java
package wsMetier;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.Contact;
import data.DataContacts;

@Path ("/lesContacts")
public class ServiceContact {
    @GET
    @Path("getContact/{refContact}")
    @Produces (MediaType.APPLICATION_JSON)
    public Contact getContactById(@PathParam ("refContact") int idContact){
        return DataContacts.lstContacts.get(idContact);
    }
    @GET
    @Path("getContacts")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Contact> getAllContact(){
        return DataContacts.lstContacts;
    }
}
```

## Appel d’un Web service ReST:

**Code Java:**

```java
public class MainActivity extends AppCompatActivity {
    TextView txtFluxJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFluxJSON = (TextView)findViewById(R.id.txtDataWS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urlService=new URL("http://192.168.1.10:8080/WSContact/lesContacts/getContact/0");
                    HttpURLConnection urlCnx =(HttpURLConnection) urlService.openConnection();
                    urlCnx.setRequestMethod("GET");
                    InputStream in = new BufferedInputStream(urlCnx.getInputStream());
                    Scanner sc=new Scanner(in);
                    Gson gson=new GsonBuilder().create();
                    final Contact leContact =gson.fromJson(sc.nextLine(), Contact.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtFluxJSON.setText(leContact.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
```
