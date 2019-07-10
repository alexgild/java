package ru.nsu.ccfit.gild.factory;

public class DetailFactory {
    public static Detail createDetail(String detailName, int delay) throws NullPointerException, InterruptedException {
        switch (detailName) {
            case "Body":
                return createBody(delay);
            case "Motor":
                return createMotor(delay);
            case "Accessory":
                return createAccessory(delay);
            default:
                System.out.println("You've tried to make a detail with name " + detailName + ". Seriously?");
                return null;
        }
    }

   private static Body createBody(int delay) throws InterruptedException {
       return new Body(delay);
    }

    private static Motor createMotor(int delay) throws InterruptedException {
        return new Motor(delay);
    }

    private static Accessory createAccessory(int delay) throws InterruptedException {
        return new Accessory(delay);
    }
}
