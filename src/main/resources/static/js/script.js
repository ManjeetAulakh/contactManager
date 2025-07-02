// for changing light/dark button

const toggleBtn = document.getElementById('theme-toggle');
const label = document.getElementById('theme-label');
const icon = document.getElementById('theme-icon');

toggleBtn.addEventListener('click', () => {
  const html = document.documentElement;
  html.classList.toggle('dark');

  const isDark = html.classList.contains('dark');

  // Show the next mode (i.e., what clicking will do)
  if (isDark) {
    label.textContent = 'Light';                // Next option to switch to
    icon.classList.remove('fa-moon');
    icon.classList.add('fa-sun');
  } else {
    label.textContent = 'Dark';
    icon.classList.remove('fa-sun');
    icon.classList.add('fa-moon');
  }
});


// for menu button showi in mobiles 
const mobileToggleBtn = document.getElementById('mobile-menu-button');
    const mobileMenu = document.getElementById('mobile-menu');
    if (mobileToggleBtn && mobileMenu) {
      mobileToggleBtn.addEventListener('click', () => {
        mobileMenu.classList.toggle('hidden');
      });
    }