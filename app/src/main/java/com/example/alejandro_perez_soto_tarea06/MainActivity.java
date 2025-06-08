package com.example.alejandro_perez_soto_tarea06;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1; // Código de solicitud de permisos de ubicación

    private GoogleMap mMap; // Referencia al mapa
    private SwitchCompat switchLocation; // Switch para mostrar/ocultar la ubicación

    private FusedLocationProviderClient fusedLocationClient; // Cliente para obtener ubicación actual
    private final Map<Marker, CrashLocation> markerLocationMap = new HashMap<>(); // Mapa de marcadores y retos

    // Coordenadas de las ubicaciones en Andalucía
    private final LatLng[] positions = new LatLng[]{
            new LatLng(37.3891, -5.9845),
            new LatLng(36.5271, -6.2886),
            new LatLng(37.1773, -3.5986),
            new LatLng(37.8882, -4.7794),
            new LatLng(36.7213, -4.4214),
            new LatLng(36.8340, -2.4637),
            new LatLng(37.7796, -3.7849),
            new LatLng(37.2614, -6.9447),
            new LatLng(36.6850, -6.1261),
            new LatLng(36.5101, -4.8825)
    };

    // Títulos de los retos
    private final String[] titles = {
            "Templo Uka Uka", "Isla N. Sanity", "Cueva de Cortex",
            "Laboratorio de N. Gin", "Jungla de Ripper Roo", "Ruinas de Aku Aku",
            "Castillo de Cortex", "Pantano de Papu Papu", "Templo del Tiempo", "Volcán Crash"
    };

    // Descripción breve de cada reto
    private final String[] snippets = {
            "Encuentra el tótem y repite el hechizo.",
            "Recolecta 3 frutas Wumpa escondidas.",
            "Resuelve el acertijo de los cristales.",
            "Desactiva el láser sin ser detectado.",
            "Esquiva las trampas naturales de la jungla.",
            "Puzzle de las máscaras ancestrales.",
            "Roba el mapa secreto del castillo.",
            "Encuentra el camino correcto entre la niebla.",
            "Ordena las estatuas en la secuencia correcta.",
            "Recolecta 5 gemas antes de la erupción."
    };

    // Contraseñas para completar cada reto
    private final String[] passwords = {
            "uka2024", "wumpafruit", "cristal123", "laserzero", "ripjump",
            "maskpower", "cortexkey", "papupath", "timesequence", "eruption5"
    };

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el proveedor de ubicación
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Referencia al switch para activar la ubicación
        switchLocation = findViewById(R.id.switch_location);

        // Maneja el evento de activar/desactivar el switch de ubicación
        switchLocation.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                requestLocationPermission(); // Solicita permisos si es necesario
            } else if (mMap != null) {
                mMap.setMyLocationEnabled(false); // Desactiva capa de ubicación
            }
        });

        // Configura el fragmento del mapa
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this); // Espera a que el mapa esté listo
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Añade los marcadores al mapa
        for (int i = 0; i < titles.length; i++) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(positions[i])
                    .title(titles[i])
                    .snippet(snippets[i])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.crash)));

            // Asocia el marcador con la información del reto
            markerLocationMap.put(marker, new CrashLocation(titles[i], snippets[i], positions[i], passwords[i]));
        }

        // Maneja los clics en el InfoWindow del marcador
        mMap.setOnInfoWindowClickListener(marker -> {
            CrashLocation loc = markerLocationMap.get(marker);
            if (loc != null) {
                showChallengeDialog(loc); // Muestra el reto asociado
            }
        });
    }

    // Solicita permiso de ubicación si aún no está concedido
    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            showMyLocationOnMap(); // Ya hay permiso → muestra ubicación
        }
    }

    // Muestra la ubicación actual del usuario en el mapa
    private void showMyLocationOnMap() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true); // Activa capa "mi ubicación"

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current, 10)); // Mueve la cámara
                        }
                    });
        }
    }

    // Respuesta al cuadro de diálogo de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMyLocationOnMap(); // Permiso aceptado
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
                switchLocation.setChecked(false); // Apaga el switch si se niega
            }
        }
    }

    // Muestra un diálogo con el reto y campo para ingresar la contraseña
    private void showChallengeDialog(CrashLocation loc) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_challenge, null);
        TextView descriptionText = dialogView.findViewById(R.id.text_description);
        EditText passwordField = dialogView.findViewById(R.id.edit_password);
        Button finishButton = dialogView.findViewById(R.id.button_finish);

        descriptionText.setText(loc.instructions);
        finishButton.setEnabled(false); // Botón desactivado hasta escribir algo

        // Habilita el botón solo si se escribe una contraseña
        passwordField.addTextChangedListener(new android.text.TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                finishButton.setEnabled(s.toString().trim().length() > 0);
            }
            @Override public void afterTextChanged(android.text.Editable s) {}
        });

        // Crea el AlertDialog
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(loc.title)
                .setView(dialogView)
                .setNegativeButton("Cancelar", null)
                .create();

        // Valida la contraseña al hacer clic en "Finalizar actividad"
        finishButton.setOnClickListener(v -> {
            String input = passwordField.getText().toString().trim();
            if (input.equals(loc.password)) {
                dialog.dismiss();
                showCompletionDialog(loc.title); // Reto completado
            } else {
                passwordField.setError("Contraseña incorrecta"); // Error
            }
        });

        dialog.show();
    }

    // Muestra una confirmación al completar el reto
    private void showCompletionDialog(String title) {
        new AlertDialog.Builder(this)
                .setTitle("¡Actividad completada!")
                .setMessage("Has completado el reto: " + title)
                .setPositiveButton("Aceptar", null)
                .show();
    }
}
