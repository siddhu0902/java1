package Utils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * NetworkUtils - Network-related utility functions
 * IP validation, traffic simulation, etc.
 */
public class NetworkUtils {

    private static final String IPV4_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final String IPV6_PATTERN =
            "^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$";

    private static final String MAC_PATTERN =
            "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";

    private static Pattern ipv4Pattern = Pattern.compile(IPV4_PATTERN);
    private static Pattern ipv6Pattern = Pattern.compile(IPV6_PATTERN);
    private static Pattern macPattern = Pattern.compile(MAC_PATTERN);
    private static Random random = new Random();

    /**
     * Validate IPv4 address
     * @param ip IP address to validate
     * @return true if valid IPv4
     */
    public static boolean isValidIPv4(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        return ipv4Pattern.matcher(ip).matches();
    }

    /**
     * Validate IPv6 address
     * @param ip IP address to validate
     * @return true if valid IPv6
     */
    public static boolean isValidIPv6(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        return ipv6Pattern.matcher(ip).matches();
    }

    /**
     * Validate IP address (IPv4 or IPv6)
     * @param ip IP address to validate
     * @return true if valid IP
     */
    public static boolean isValidIP(String ip) {
        return isValidIPv4(ip) || isValidIPv6(ip);
    }

    /**
     * Validate MAC address
     * @param mac MAC address to validate
     * @return true if valid MAC
     */
    public static boolean isValidMAC(String mac) {
        if (mac == null || mac.isEmpty()) {
            return false;
        }
        return macPattern.matcher(mac).matches();
    }

    /**
     * Generate random IPv4 address
     * @return Random IPv4 address
     */
    public static String generateRandomIPv4() {
        return random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256) + "." +
                random.nextInt(256);
    }

    /**
     * Generate random private IPv4 address (10.x.x.x, 172.16.x.x, 192.168.x.x)
     * @return Random private IP
     */
    public static String generatePrivateIPv4() {
        int type = random.nextInt(3);
        switch (type) {
            case 0: // 10.x.x.x
                return "10." + random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256);
            case 1: // 172.16.x.x - 172.31.x.x
                return "172." + (16 + random.nextInt(16)) + "." + random.nextInt(256) + "." + random.nextInt(256);
            default: // 192.168.x.x
                return "192.168." + random.nextInt(256) + "." + random.nextInt(256);
        }
    }

    /**
     * Check if IP is private (RFC 1918)
     * @param ip IP address
     * @return true if private
     */
    public static boolean isPrivateIP(String ip) {
        if (!isValidIPv4(ip)) {
            return false;
        }

        String[] parts = ip.split("\\.");
        int first = Integer.parseInt(parts[0]);
        int second = Integer.parseInt(parts[1]);

        // 10.0.0.0/8
        if (first == 10) return true;
        // 172.16.0.0/12
        if (first == 172 && second >= 16 && second <= 31) return true;
        // 192.168.0.0/16
        if (first == 192 && second == 168) return true;

        return false;
    }

    /**
     * Generate random port number
     * @return Random port (1-65535)
     */
    public static int generateRandomPort() {
        return random.nextInt(65535) + 1;
    }

    /**
     * Check if port is well-known (0-1023)
     * @param port Port number
     * @return true if well-known
     */
    public static boolean isWellKnownPort(int port) {
        return port >= 0 && port <= 1023;
    }

    /**
     * Check if port is registered (1024-49151)
     * @param port Port number
     * @return true if registered
     */
    public static boolean isRegisteredPort(int port) {
        return port >= 1024 && port <= 49151;
    }

    /**
     * Check if port is dynamic/private (49152-65535)
     * @param port Port number
     * @return true if dynamic
     */
    public static boolean isDynamicPort(int port) {
        return port >= 49152 && port <= 65535;
    }

    /**
     * Get service name for common ports
     * @param port Port number
     * @return Service name
     */
    public static String getServiceName(int port) {
        switch (port) {
            case 20: return "FTP Data";
            case 21: return "FTP Control";
            case 22: return "SSH";
            case 23: return "Telnet";
            case 25: return "SMTP";
            case 53: return "DNS";
            case 80: return "HTTP";
            case 110: return "POP3";
            case 143: return "IMAP";
            case 443: return "HTTPS";
            case 3389: return "RDP";
            case 3306: return "MySQL";
            case 5432: return "PostgreSQL";
            case 27017: return "MongoDB";
            default: return "Unknown";
        }
    }

    /**
     * Simulate network traffic (for demonstration)
     * @param packets Number of packets to simulate
     * @param sourceIP Source IP
     * @param destIP Destination IP
     * @return Simulation result
     */
    public static String simulateTraffic(int packets, String sourceIP, String destIP) {
        StringBuilder result = new StringBuilder();
        result.append("\n┌─────────────────────────────────────────────────────────┐");
        result.append("\n│ TRAFFIC SIMULATION                                      │");
        result.append("\n├─────────────────────────────────────────────────────────┤");
        result.append("\n│ Source: ").append(sourceIP);
        result.append("\n│ Destination: ").append(destIP);
        result.append("\n│ Packets: ").append(packets);
        result.append("\n├─────────────────────────────────────────────────────────┤");

        int sent = 0;
        int received = 0;
        int lost = 0;

        for (int i = 0; i < packets; i++) {
            sent++;
            // Simulate packet loss (5% chance)
            if (random.nextDouble() > 0.05) {
                received++;
            } else {
                lost++;
            }
        }

        result.append("\n│ Packets Sent: ").append(sent);
        result.append("\n│ Packets Received: ").append(received);
        result.append("\n│ Packets Lost: ").append(lost);
        result.append("\n│ Loss Rate: ").append(String.format("%.1f", (lost * 100.0 / sent))).append("%");
        result.append("\n└─────────────────────────────────────────────────────────┘");

        return result.toString();
    }

    /**
     * Calculate network latency based on distance (simulated)
     * @param distanceKm Distance in kilometers
     * @return Latency in milliseconds
     */
    public static int calculateLatency(int distanceKm) {
        // Speed of light in fiber ~200,000 km/s
        // Round trip latency = 2 * distance / speed
        return (int)(2 * distanceKm / 200.0) + random.nextInt(5);
    }

    /**
     * Get CIDR notation from subnet mask
     * @param subnetMask Subnet mask (e.g., "255.255.255.0")
     * @return CIDR prefix length
     */
    public static int subnetMaskToCIDR(String subnetMask) {
        if (!isValidIPv4(subnetMask)) {
            return -1;
        }

        int cidr = 0;
        String[] parts = subnetMask.split("\\.");

        for (String part : parts) {
            int octet = Integer.parseInt(part);
            while (octet > 0) {
                cidr += octet & 1;
                octet >>= 1;
            }
        }

        return cidr;
    }

    /**
     * Get subnet mask from CIDR notation
     * @param cidr CIDR prefix length (0-32)
     * @return Subnet mask
     */
    public static String cidrToSubnetMask(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return "Invalid CIDR";
        }

        int mask = 0xFFFFFFFF << (32 - cidr);
        return ((mask >> 24) & 0xFF) + "." +
                ((mask >> 16) & 0xFF) + "." +
                ((mask >> 8) & 0xFF) + "." +
                (mask & 0xFF);
    }

    /**
     * Generate random MAC address
     * @return Random MAC address
     */
    public static String generateRandomMAC() {
        byte[] mac = new byte[6];
        random.nextBytes(mac);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X", mac[i] & 0xFF));
            if (i < mac.length - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }
}