package rh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Storage {
    private Vehicle template;
    private Level   lvl;
    private ArrayList<Level> levels;

    public Storage() {
        levels = new ArrayList<>();
    }

    private String takeLevelsFromFile() throws Exception {
        File file = new File("src/rh/levels.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st = "";
        String result = "";
        while ((st = br.readLine()) != null) {
            //System.out.println(st);
            result += st;
        }
        return result;
    }

    public void printFile(){
        try {
            System.out.print(takeLevelsFromFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Level> createsVehicles(String str) {
        //Vehicle[] fleet = new Vehicle[20];


        int index = 0; // for fleet array
        int indexLevel = 0; // for levels array ex=> 0: first level 1: second level
        for ( int i = 0; i < str.length(); i++) {
            ArrayList<Vehicle> fleet = new ArrayList<>();
            if (str.charAt(i) == ':') {
                for (int j = i + 1; j < str.length(); j = j + 12) {
                    int x = (int) Integer.parseInt(str.substring(j + 1, j + 2));
                    int y = (int) Integer.parseInt(str.substring(j + 3, j + 4));
                    String direction = str.substring(j + 7, j + 8);
                    int length = (int) Integer.parseInt(str.substring(j + 11, j + 12));
                    //System.out.println("x: " + x + " y: " + y + " direction: " + direction + " length: " + length);
                    if (j == i + 1) {
                        template = new Car();
                    } else {
                        template = new Vehicle();
                    }
                    template.createVehicle(x, y, length, direction);
                    fleet.add(template);
                    //System.out.print("\n"+fleet[index].getDirection());
                    index++;
                    if (str.charAt(j + 12) == '&') {
                        if(indexLevel == 12){
                            break;
                        }
                        //System.out.println( " level tamam" );
                        //System.out.println( fleetA[0]);
                        lvl = new Level();
                        lvl.createLevel(fleet, 2, 5, (Car) (fleet.get(0)));
                        levels.add(lvl);
                        indexLevel++;
                        break;
                    }
                    if (indexLevel == 2)
                        break;
                }
            }
        }
        return levels;
    }

    public ArrayList<Level> getLevels() throws Exception {
        return this.createsVehicles( this.takeLevelsFromFile());
    }

    public Level getLevel(int index)throws Exception{
        return (this.createsVehicles( this.takeLevelsFromFile())).get(index);
    }
}

