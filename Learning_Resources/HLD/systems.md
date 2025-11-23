# Netflix

The key components in the high-level architecture for video streaming include:

- **API Gateway**: The entry point for client requests; routes requests to the appropriate services.
- **Video Playback Service**: Manages streaming logic and orchestrates reads from cache, metadata, and object storage.
- **Object Storage**: Stores the raw video files (e.g., S3, GCS).
- **CDN (Content Delivery Network)**: Distributes cached video content to users to minimize latency.

Read Path:

When a user requests a video, the load balancer and API Gateway route the request to the Video Playback Service. The
playback service retrieves metadata (URL, codecs, segments) from the Video Metadata Storage and serves video data
from caching layers when possible. If a cache miss occurs, the service fetches segments from object storage and the
nearest CDN edge node streams the content to the user for a smooth playback experience.

The CDN significantly reduces latency by delivering cached videos from nodes close to users. The metadata database
stores titles, descriptions, and user interactions (likes, comments) and is optimized for high read throughput.

API Design

```
GET    /videos                      # Retrieves a list of videos
POST   /videos                      # Uploads a new video
GET    /videos/{videoId}/play       # Play a video
POST   /users/{userId}/history      # Update user's viewing history
```

![Netflix architecture](../../static/HLD/netflix2.png)

