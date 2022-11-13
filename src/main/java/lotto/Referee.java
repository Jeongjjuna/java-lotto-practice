package lotto;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referee {
    public static Map<Integer, Integer> result;

    public static void initHashMap() {
        result = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            result.put(i, 0);
        }
    }

    public static void countNumberRank(int correctNumber, int bonusNumber, List<Integer> user) {
        //{0=3, 1=4, 2=1, 3=0, 4=0, 5=0, 6=0, 7=0} = {-,-,-,5등, 4등, 3등, 2등, 1등}
        boolean checkSecondPrize = (correctNumber == 5 && Judgment.hasBonusNumber(bonusNumber, user));
        boolean checkFirstPrize = (correctNumber == 6);

        if (checkFirstPrize || checkSecondPrize) {
            result.replace(correctNumber + 1, result.get(correctNumber + 1) + 1);
            return;
        }
        result.replace(correctNumber, result.get(correctNumber) + 1);

    }

    public static Map<Integer, Integer> compare(int bonusNumber, List<Integer> winningLotto, List<Lotto> userLotto) {

        initHashMap(); // result 초기화

        for (Lotto lottoClass : userLotto) {
            List<Integer> user = lottoClass.get();
            System.out.println(user);

            int correctNumber = Judgment.correctCount(winningLotto, user);
            countNumberRank(correctNumber, bonusNumber, user);
        }

        return result;
    }
}
