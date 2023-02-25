import Button from '../components/Button';
import Container from '../components/Container';
import Lined from '../components/Lined';
import styles from './HomePage.module.css';

function HomePage() {
  return (
    <>
      <div className={styles.bg} />
      <Container className={styles.container}>
        <div className={styles.texts}>
          <h1 className={styles.heading}>
            <Lined>설레는 미팅을 하고 싶다면,</Lined>
            <br />
            <strong>대학미팅</strong>
          </h1>
          <p className={styles.description}>
            설레는 봄, 우리 대학미팅과 함께 하세요!
            <br />
            지금 함께 시작해보실래요?
          </p>
          <div>
            <Button>지금 시작하기</Button>
          </div>
        </div>
        <div className={styles.figure}>
          <img src={landingImg} alt="연애, 사랑, 미팅, 대학미팅" />
        </div>
      </Container>
    </>
  );
}

export default HomePage;
