/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesVariables;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author mdsad
 */
public class GetPropertiesValues {

    public static Properties var;

    public static Properties getPropertiesInstance(String fileName) throws FileNotFoundException, IOException {
        if (var == null) {
            var = new Properties();
            try (FileInputStream inputStram = new FileInputStream(fileName)) {
                var.load(inputStram);
            }
            return var;
        } else {
            return var;
        }

    }

}
