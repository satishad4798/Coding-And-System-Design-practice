# Netflix

The key components in the high-level architecture for video streaming include the following:

API Gateway: The entry point for all client requests, directing to appropriate services.
Video Playback Service: Manages video streaming logic and directs requests to video storage or CDN.
Object Storage: Stores the actual video files.
CDN (Content Delivery Network): Distributes video content to users to minimize latency.
Read Path: When a user initiates a request to view a video, this request is processed by the load balancer and API
Gateway, which then routes it to the Video Playback Service. This service efficiently retrieves video data through
caching layers optimized for quick access, before accessing the Video Metadata Storage for the video's URL. Once
retrieved, the video is streamed from the nearest CDN node to the user's device, ensuring a seamless playback
experience.

The Content Delivery Network (CDN) is crucial for delivering cached videos from a location nearest to the user,
significantly reducing latency and enhancing the viewing experience.
The metadata database is responsible for managing video titles, descriptions, and user interactions such as likes and
comments. These databases are optimized to support high volumes of read operations efficiently.

API Design
GET /videos - Retrieves a list of videos.
POST /videos - Uploads a new video.
GET /videos/{videoId}/play - Streams a video.
POST /users/{userId}/history - Updates user's viewing history.

![alt text](../../static/HLD/netflix2.png)

