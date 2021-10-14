document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById('registration_form');
    form.addEventListener('submit', (e) => {
        e.preventDefault()

        const inputs = document.querySelectorAll('.form_input_container')
        let errors = 0;

        let validPasswords = true

        inputs.forEach(el => {
            el.classList.remove('_error')
            el.classList.remove('_bad-passwords')
            if(el.classList.contains('firstname')){
                if(!/^[A-zА-яЁё`'.-]{1,32}$/.test(el.querySelector('input').value)){
                    errors++
                    el.classList.add("_error")
                }
            }
            if(el.classList.contains('lastname')){
                if(!/^[A-zА-яЁё`'.-]{1,32}$/.test(el.querySelector('input').value)){
                    errors++
                    el.classList.add("_error")
                }
            }
            if(el.classList.contains('email')){
                if(!/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(el.querySelector('input').value)){
                    errors++
                    el.classList.add("_error")
                }
            }
            if(el.classList.contains('password')){
                if(!/^\w{6,20}$/.test(el.querySelector('input').value)){
                    validPasswords = false
                    errors++
                    el.classList.add("_error")
                }
            }
        })

        if(validPasswords){
            const password_1 = document.getElementById('password_1')
            const password_2 = document.getElementById('password_2')
            if(password_1.value != password_2.value){
                password_1.parentElement.classList.add('_bad-passwords')
                password_2.parentElement.classList.add('_bad-passwords')
                errors++
            }
        }

        if (!errors) {
            form.submit()
        }
    })
})

