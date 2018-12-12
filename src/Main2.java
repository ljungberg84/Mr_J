import model.Login;
import model.Program;
import parsers.*;

public class Main2 {

    public static void main(String[] args) {
        ServiceParser hboParser = new HboParser();
        ServiceParser netflixParser = new NetflixParser();
        ServiceParser viaplayParser = new ViaplayParser();
        ServiceParser showtime = new ShowtimeParser();
        ServiceParser svtplayParser = new SVTPlayParser();

        Program program = new Program();

        //program.addService("HBO Nordic", hboParser);
        program.addService("Netflix", netflixParser);
        //program.addService("Viaplay", viaplayParser);
        //program.addService(showtime);
        //program.addService("SVT Play", svtplayParser);
        program.start("better call saul");

        //Login login = new Login("https://www.netflix.com/se/login");
        //login.parse("");
        //NetflixParser netflix = new NetflixParser();
        //netflix.parse("milk");
    }
}
