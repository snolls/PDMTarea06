package com.example.alejandro_perez_soto_tarea06;

import com.google.android.gms.maps.model.LatLng;

public class CrashLocation {
    public String title;
    public String instructions;
    public LatLng position;
    public String password;

    public CrashLocation(String title, String instructions, LatLng position, String password) {
        this.title = title;
        this.instructions = instructions;
        this.position = position;
        this.password = password;
    }
}
