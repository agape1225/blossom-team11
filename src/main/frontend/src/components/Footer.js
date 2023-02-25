import logo from '../assets/grayLogo.svg';
import facebookIcon from '../assets/facebook.svg';
import twitterIcon from '../assets/twitter.svg';
import instagramIcon from '../assets/instagram.svg';
import styles from './Footer.module.css';
import Container from './Container';

function Footer() {
  return (
    <div className={styles.footer}>
      <Container>
        <ul className={styles.links}>
          <li>벚꽃팀 11팀</li>
          <li>기획/ 디자이너</li>
          <li>프론트엔드</li>
          <li>백엔드</li>
        </ul>
        <ul className={styles.info}>
          <li>벚꽃톤</li>
          <li>대표 | 성창규</li>
          <li>기획/디자인 | 윤시현 </li>
          <li>프론트엔드 | 오소현 </li>
          <li>백엔드 | 성창규, 이하진, 김현우 </li>
        </ul>
        <div className={styles.icons}>
          <div className={styles.sns}>
            <img src={facebookIcon} alt="facebook icon" />
            <img src={twitterIcon} alt="twitter icon" />
            <img src={instagramIcon} alt="instagram icon" />
          </div>
        </div>
      </Container>
    </div>
  );
}

export default Footer;
