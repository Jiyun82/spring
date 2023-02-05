package HamstoryBack.HamstoryBack.service;

import HamstoryBack.HamstoryBack.domain.Member;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    Firestore firestore = FirestoreClient.getFirestore();

    public static final String COLLECTION_NAME = "MEMBER";

    public void insertMember(Member member) throws Exception{
        validateDuplicateMember(member.getEmail());
        ApiFuture<WriteResult> apiFuture = firestore.collection(COLLECTION_NAME).document(member.getEmail()).set(member);
    }

    public void validateDuplicateMember(String email) throws Exception{
        Member member = getMemberDetail(email);
        if(member != null){//이메일이 중복된다면
            throw new IllegalStateException("중복되는 이메일 입니다.");
        }
    }

    public Member getMemberDetail(String email) throws Exception{
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(email);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();

        Member member = null;
        if(documentSnapshot.exists()){
            member = documentSnapshot.toObject(Member.class);
            return member;
        }else{
            return null;
        }
    }
}

