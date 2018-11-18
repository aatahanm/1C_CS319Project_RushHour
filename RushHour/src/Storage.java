import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Storage {
    private Vehicle template;
    private Level   lvl;
    private Level[] levels;

    public Storage() {
        levels = new Level[5];
    }

    private String takeLevelsFromFile() throws Exception {
        File file = new File("C:\\Users\\ahmet\\Desktop\\Project319\\1C_CS319Project_RushHour\\levels.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st = "";
        String result = "";
        while ((st = br.readLine()) != null) {
            //System.out.println(st);
            result += st;
        }
        return result;
    }
    private Level[] createsVehicles(String str) {
        Vehicle[] fleet = new Vehicle[20];

        int index = 0; // for fleet array
        int indexLevel = 0; // for levels array ex=> 0: first level 1: second level
        for ( int i = 0; i < str.length(); i++)
            if (str.charAt(i) == ':') {
                for (int j = i + 1; j < str.length(); j = j + 12) {
                    int x = (int) Integer.parseInt(str.substring(j + 1, j + 2));
                    int y = (int) Integer.parseInt(str.substring(j + 3, j + 4));
                    String direction = str.substring(j + 7, j + 8);
                    int length = (int) Integer.parseInt(str.substring(j + 11, j + 12));
                    System.out.println("x: " + x + " y: " + y + " direction: " + direction + " length: " + length);
                    template = new Vehicle();
                    template.createVehicle(x, y, length, direction);
                    fleet[index] = template;
                    index++;
                    if (str.charAt(j+12) == '&') {
                        System.out.println( " level tamam" );
                        lvl = new Level();
                        lvl.createLevel( fleet, 2,5, (Car)(fleet[ fleet.length - 1]));
                        levels[ indexLevel] = lvl;
                        indexLevel++;
                        break;
                    }
                }
            }
        return levels;
    }

    public Level[] getLevels() throws Exception {   
        return this.createsVehicles( this.takeLevelsFromFile());
    }
}

