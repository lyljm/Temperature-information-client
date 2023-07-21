package org.com.RDcenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gun implements Serializable {
    String GunSN;
    String GunMac;
    String GunName;
    String Description;
}
