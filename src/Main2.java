import model.Program;
import parsers.*;

public class Main2 {

    public static void main(String[] args) {
        ServiceParser hboParser = new HboParser();
        ServiceParser netflixParser = new NetflixParser();
        ServiceParser viaplayParser = new ViaplayParser();
        ServiceParser showtime = new ShowtimeParser();
        ServiceParser svtplay = new SVTPlayParser();

        Program program = new Program();

        program.addService("hbo", hboParser);
        //program.addService("netflix", netflixParser);
        program.addService("viaplay", viaplayParser);
        //program.addService(showtime);
        program.addService("svtplay", svtplay);
        program.start();
    }
}
