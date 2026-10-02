import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Represents an active check-in
    private static class CheckInInfo {
        String stationName;
        int checkInTime;

        CheckInInfo(String stationName, int checkInTime) {
            this.stationName = stationName;
            this.checkInTime = checkInTime;
        }
    }

    // Aggregates total trip time and count between two stations
    private static class RouteStats {
        double totalTime;
        int tripCount;

        RouteStats(double totalTime, int tripCount) {
            this.totalTime = totalTime;
            this.tripCount = tripCount;
        }

        void addTrip(int duration) {
            this.totalTime += duration;
            this.tripCount++;
        }

        double getAverage() {
            return this.totalTime / this.tripCount;
        }
    }

    // id -> CheckInInfo
    private final Map<Integer, CheckInInfo> checkInMap;
    // "startStation->endStation" -> RouteStats
    private final Map<String, RouteStats> routeStatsMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeStatsMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        // Retrieve and remove customer's active trip in one step
        CheckInInfo checkInInfo = checkInMap.remove(id);

        String routeKey = checkInInfo.stationName + "->" + stationName;
        int duration = t - checkInInfo.checkInTime;

        // Update aggregated statistics for the route
        routeStatsMap.computeIfAbsent(routeKey, k -> new RouteStats(0, 0))
                     .addTrip(duration);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        return routeStatsMap.get(routeKey).getAverage();
    }
}