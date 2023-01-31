public class Umpire {
    private final String Name;

    Umpire() {
        Name = "Kumar DharmaSena.";
    }

    void Wicket() {
        /*
            Function which helps umpire to signal wicket.
        */
        System.out.println("Its a Wicket");
    }

    void Four() {
        /*
            Function which helps umpire to signal four.
        */
        System.out.println("Its a four");
    }

    void Six() {
        /*
            Function which helps umpire to signal Six.
        */
        System.out.println("Its a Six");
    }

    void Wide() {
        /*
            Function which helps umpire to signal Wide.
        */
        System.out.println("Its a wide");
    }

    void NoBall() {
        /*
            Function which helps umpire to signal NoBall.
        */
        System.out.println("Its a NoBall and a FreeHit!!!");
    }

    void Runs(char RunsScored) {
        /*
            Runs scored other than 4 or 6.
        */
        System.out.println("On this ball BatsMan scored " + RunsScored);
    }

    void Signal(char OutcomeOfTheBall) {
        /*
            Helps Umpire decides to signal different function depending on the OutcomeOfTheBall.
        */
        if (OutcomeOfTheBall == 'W')
            Wicket();
        else if (OutcomeOfTheBall == '4')
            Four();
        else if (OutcomeOfTheBall == '6')
            Six();
        else
            Runs(OutcomeOfTheBall);
    }

}

