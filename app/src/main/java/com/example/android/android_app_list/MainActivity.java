package com.example.android.android_app_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Lista de usuários
    ArrayList<UsuarioModel> usuarioModels;
    ListView listView;
    private static UsuarioAdapter adapter;

    private AlertDialog alerta;

    View alertaView;

    public Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineLateralMenu();

        defineNavigationView();

        //defineListViewHome();

        defineBottomNavigationView();

        setListView();

        setUsuarioListViewHome();

        createAlertDialog();

        defineFloatingButton();
    }

    private void setUsuarioListViewHome() {

        final LinearLayout.LayoutParams paramsListView = (LinearLayout.LayoutParams) listView.getLayoutParams();

        paramsListView.setMargins(
                paramsListView.leftMargin,
                paramsListView.topMargin,
                paramsListView.rightMargin,
                125
        );

        listView.setLayoutParams(paramsListView);

        usuarioModels = new ArrayList<>();

        usuarioModels.add(new UsuarioModel("Apple Pie", "Android 1.0", "1","September 23, 2008"));
        usuarioModels.add(new UsuarioModel("Banana Bread", "Android 1.1", "2","February 9, 2009"));
        usuarioModels.add(new UsuarioModel("Cupcake", "Android 1.5", "3","April 27, 2009"));
        usuarioModels.add(new UsuarioModel("Donut","Android 1.6","4","September 15, 2009"));
        usuarioModels.add(new UsuarioModel("Eclair", "Android 2.0", "5","October 26, 2009"));
        usuarioModels.add(new UsuarioModel("Froyo", "Android 2.2", "8","May 20, 2010"));
        usuarioModels.add(new UsuarioModel("Gingerbread", "Android 2.3", "9","December 6, 2010"));
        usuarioModels.add(new UsuarioModel("Honeycomb","Android 3.0","11","February 22, 2011"));
        usuarioModels.add(new UsuarioModel("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011"));
        usuarioModels.add(new UsuarioModel("Jelly Bean", "Android 4.2", "16","July 9, 2012"));
        usuarioModels.add(new UsuarioModel("Kitkat", "Android 4.4", "19","October 31, 2013"));
        usuarioModels.add(new UsuarioModel("Lollipop","Android 5.0","21","November 12, 2014"));
        usuarioModels.add(new UsuarioModel("Marshmallow", "Android 6.0", "23","October 5, 2015"));

        adapter = new UsuarioAdapter(usuarioModels,getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UsuarioModel dataModel= usuarioModels.get(position);

                Snackbar snackbar = Snackbar.make(view, dataModel.getNome()+"\n"+dataModel.getTipo()+" "+dataModel.getNumeroDaVersao(), Snackbar.LENGTH_LONG);

                View snackBarView = snackbar.getView();
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackBarView.getLayoutParams();

                View navigationView = findViewById(R.id.fab);

                params.setMargins(
                        params.leftMargin,
                        params.topMargin,
                        params.rightMargin,
                        navigationView.getHeight()
                );

                snackBarView.setLayoutParams(params);

                snackbar.setAction("No action", null).show();
            }
        });
    }

    private void defineFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Exibe
                alerta.show();
            }
        });
    }

    private void addNewUser() {

        Boolean valido = true;

        EditText nome = alertaView.findViewById(R.id.nomeDialog);
        EditText tipo = alertaView.findViewById(R.id.tipoDialog);
        EditText numeroVersao = alertaView.findViewById(R.id.numeroDaVersaoDialog);
        EditText lancamento = alertaView.findViewById(R.id.dialogDataLancamento);

        if( nome.getText().toString().length() == 0 ) {
            nome.setError( "Nome é obrigatório!" );
            valido = false;
        }

        if( tipo.getText().toString().length() == 0 ) {
            tipo.setError( "Tipo é obrigatório!" );
            valido = false;
        }

        if( numeroVersao.getText().toString().length() == 0 ) {
            numeroVersao.setError( "Número da versão é obrigatório!" );
            valido = false;
        }

        String lancamentoInput = lancamento.getText().toString();

        if( lancamentoInput.length() == 0 || !lancamentoInput.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
            lancamento.setText("");
            lancamento.setError( "Data de lançamento inválida!" );
            valido = false;
        }

        if (valido) {
            usuarioModels.add(new UsuarioModel("" + nome.getText(), "" + tipo.getText(), "" + numeroVersao.getText(), "" + lancamento.getText()));

            adapter = new UsuarioAdapter(usuarioModels,getApplicationContext());

            listView.setAdapter(adapter);

            //desfaz o alerta.
            alerta.dismiss();
        }
    }

    EditText date;

    private void createAlertDialog() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        alertaView = li.inflate(R.layout.alerta, null);

        //definimos para o botão do layout um clickListener
        alertaView.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                addNewUser();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(alertaView);

        alerta = builder.create();
    }

    private void defineLateralMenu() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void defineBottomNavigationView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void defineNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setListView() {
        listView = (ListView) findViewById(R.id.list);
    }

    private void defineListViewDashboard() {
        // Get ListView object from xml


        // Defined Array values to show in ListView
        String[] values = new String[] {
                "Dashboard Android List View",
                "Dashboard Adapter implementation",
                "Dashboard Simple List View In Android",
                "Dashboard Create List View Android",
                "Dashboard Android Example",
                "Dashboard List View Source Code",
                "Dashboard List View Array Adapter",
                "Dashboard Android List View",
                "Dashboard Adapter implementation",
                "Dashboard Simple List View In Android",
                "Dashboard Create List View Android",
                "Dashboard Android Example",
                "Dashboard List View Source Code",
                "Dashboard List View Array Adapter",
                "Dashboard Android Example List View"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        onClickListView();
    }

    private void defineListViewNotification() {


        String[] values = new String[] {
                "Notification Android List View",
                "Notification Adapter implementation",
                "Notification Simple List View In Android",
                "Notification Create List View Android",
                "Notification Android Example",
                "Notification List View Source Code",
                "Notification List View Array Adapter",
                "Notification Android List View",
                "Notification Adapter implementation",
                "Notification Simple List View In Android",
                "Notification Create List View Android",
                "Notification Android Example",
                "Notification List View Source Code",
                "Notification List View Array Adapter",
                "Notification Android Example List View"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        onClickListView();
    }

    private void onClickListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Integer itemPosition = position;

                String  itemValue    = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }

        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the navigation; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setUsuarioListViewHome();
                    return true;
                case R.id.navigation_dashboard:
                    defineListViewDashboard();
                    return true;
                case R.id.navigation_notifications:
                    defineListViewNotification();
                    return true;
            }
            return false;
        }
    };

    public void showTimePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
