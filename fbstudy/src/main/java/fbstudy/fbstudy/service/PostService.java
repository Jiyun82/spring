package fbstudy.fbstudy.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import fbstudy.fbstudy.domain.Post;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    Firestore firestore = FirestoreClient.getFirestore();

    public static final String COLLECTION_NAME = "POST";

    public void uploadPost(Post post){
        ApiFuture<DocumentReference> apiFuture = firestore.collection(COLLECTION_NAME).add(post);
        //문제...pid를 어케 자동으로 생성할 것인가
        //pid가 잇어야 게시글을 특정할 수 잇다.
        //문서id를 가져와서 pid로 설정할 수 잇나?
    }
}
