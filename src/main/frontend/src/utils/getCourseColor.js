const COLORS = {
  purple: '#d19fe9',
  green: '#7cd9c2',
  yellow: '#f7d16f',
};

function getCourseColor(code = '000') {
  const firstCode = code.charAt(0);
  switch (firstCode) {
    case 3:
    case 9:
      return COLORS.baby-pink;
    case 5:
      return COLORS.blue;
    case 1:
    case 7:
    case 8:
    default:
      return COLORS.pink;
  }
}

export default getCourseColor;
