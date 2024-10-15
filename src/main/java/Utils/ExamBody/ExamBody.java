package Utils.ExamBody;

public class ExamBody {
    public static String getExamBody(String examID){
        return "{\n" +
                "  \"id\": \""+examID+"\",\n" +
                "  \"pageSize\": 1,\n" +
                "  \"page\": 1\n" +
                "}";
    }
    public static String evaluateExamBody(String examID, String answer1 , String answer2){
        return "{\n" +
                "  \"examID\": \""+examID+"\",\n" +
                "  \"userAnswers\": [\n" +
                "    \""+answer1+"\",\n" +
                "    \""+answer2+"\"\n" +
                "  ]\n" +
                "}";
    }

}
