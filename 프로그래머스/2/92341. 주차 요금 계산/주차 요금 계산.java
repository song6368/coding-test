import java.util.*;
import java.text.*;

class Solution {
    public int[] solution(int[] fees, String[] records) throws ParseException {
        List<String> carList = new ArrayList<>();
        Map<String, Integer> seq = new HashMap<>();
        Map<String, Integer> totalTime = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        int idx = 0;

        // 차량별 순서 저장
        for (String record : records) {
            String[] r = record.split(" ");
            String carNo = r[1];

            if (!seq.containsKey(carNo)) {
                carList.add(carNo);
                seq.put(carNo, idx);
                idx++;
            }
        }

        // ** 차량 번호 오름차순 정렬 **
        Collections.sort(carList);

        int[] answer = new int[carList.size()];
        Map<String, String> lastInTime = new HashMap<>();

        // 차량별 누적 주차 시간 계산
        for (String record : records) {
            String[] r = record.split(" ");
            String time = r[0];
            String carNo = r[1];
            String inOut = r[2];

            if (inOut.equals("IN")) {
                lastInTime.put(carNo, time);
            } else {
                String inTime = lastInTime.get(carNo);
                Date date1 = format.parse(inTime);
                Date date2 = format.parse(time);
                long difference = date2.getTime() - date1.getTime();
                int minutes = (int) (difference / (1000 * 60));

                totalTime.put(carNo, totalTime.getOrDefault(carNo, 0) + minutes);
                lastInTime.remove(carNo);
            }
        }

        // 출차되지 않은 차량 처리 (23:59 출차로 계산)
        for (String carNo : lastInTime.keySet()) {
            String inTime = lastInTime.get(carNo);
            Date date1 = format.parse(inTime);
            Date date2 = format.parse("23:59");
            long difference = date2.getTime() - date1.getTime();
            int minutes = (int) (difference / (1000 * 60));

            totalTime.put(carNo, totalTime.getOrDefault(carNo, 0) + minutes);
        }

        // 요금 계산
        for (int i = 0; i < carList.size(); i++) {
            String carNo = carList.get(i);
            int tt = totalTime.getOrDefault(carNo, 0);

            if (tt <= fees[0]) {
                answer[i] = fees[1];
            } else {
                answer[i] = fees[1] + (int) Math.ceil((tt - fees[0]) / (double) fees[2]) * fees[3];
            }
        }

        return answer;
    }
}
