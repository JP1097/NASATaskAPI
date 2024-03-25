public class NASAMediaLibraryApp {
    public static void main(String[] args) {
        try {
            MarsPhoto marsPhoto = new MarsPhoto();
            marsPhoto.fetchAndPrint();

            MarsVideo marsVideo = new MarsVideo();
            marsVideo.fetchAndPrint();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
