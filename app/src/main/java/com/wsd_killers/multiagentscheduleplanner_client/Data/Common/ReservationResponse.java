package com.wsd_killers.multiagentscheduleplanner_client.Data.Common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReservationResponse {

    public int id; // id = -1 - the reservation is not possible, read explanation to see why
    public String explanation;

    public ReservationResponse(int id, String explanation) {
        this.id = id;
        this.explanation = explanation;
    }

    public static String serialize(ReservationResponse data) {
        String serializedObject = "";
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(data);
            so.flush();
            final byte[] byteArray = bo.toByteArray();
            serializedObject = android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT);

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return serializedObject;
    }

    public static ReservationResponse deserialize(String serializedReservationRequestData) {
        ReservationResponse s = null;
        try {
            final byte[] bytes = android.util.Base64.decode(serializedReservationRequestData, android.util.Base64.DEFAULT);
            byte b[] = serializedReservationRequestData.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream si = new ObjectInputStream(bi);
            s = (ReservationResponse) si.readObject();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return s;
    }
}
