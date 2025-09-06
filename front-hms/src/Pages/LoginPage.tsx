import { Button, PasswordInput, TextInput } from "@mantine/core";
import { IconHeartbeat } from "@tabler/icons-react";
import { Link, useNavigate } from "react-router-dom";
import { useForm } from "@mantine/form";
import { loginUser } from "../Service/UserService";
import { ErrorNotification, SuccessNotification } from "../Utility/NotificationUtil";
import { useState } from "react";

const LoginPage = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const form = useForm({
    initialValues: {
      email: "",
      password: "",
    },

    validate: {
      email: (value) => (/^\S+@\S+$/.test(value) ? null : "Invalid email"),
      password: (value) => (!value ? "Password is required" : null),
    },
  });

  const handleSubmit = (values: typeof form.values) => {
    setLoading(true);
     loginUser(values).then((data) => {
      console.log(data);
      SuccessNotification("Login Successful!");
      navigate("/dashboard");
    }).catch((error) => {
      console.log(error);
      ErrorNotification(error.response.data.errorMessage || "Something went wrong. Please try again.");
    }).finally(() => setLoading(false));
  };

  return (
    <div
      style={{ background: 'url("/bg.jpg")' }}
      className="h-screen w-screen !bg-cover !bg-center !bg-no-repeat flex flex-col items-center justify-center gap-4"
    >
      <Link to="/" className=" py-3 text-pink-500 flex gap-1 items-center">
        <IconHeartbeat size={45} stroke={2.5} />
        <span className="font-heading text-4xl font-semibold">Pulse</span>
      </Link>

      <div className="w-[450px] backdrop-blur-md p-8 rounded-xl">
        <form
          onSubmit={form.onSubmit(handleSubmit)}
          className="flex flex-col gap-5 [&_input]:!pl-2 [&_.mantine-Input-input]:!border-white [&_.mantine-Input-input:focus-within]:!border-pink-500 [&_.mantine-Input-input]:!border [&_svg]:text-white [&_input]:!text-white "
        >
          <div className="self-center font-medium font-heading text-2xl text-white">
            Login
          </div>
          <TextInput
            variant="unstyled"
            size="md"
            radius="md"
            placeholder="Enter your Email"
            key={form.key("email")}
            {...form.getInputProps("email")}
          />
          <PasswordInput
            variant="unstyled"
            size="md"
            radius="md"
            placeholder="Enter the Password"
            key={form.key("password")}
            {...form.getInputProps("password")}
          />
          <Button loading={loading} type="submit" size="md" radius="md" color="pink.6">
            Login
          </Button>

          <div className="self-center text-neutral-200 text-md">Don't have an account? <Link to="/register" className="hover:text-pink-600 underline" >Register</Link> </div>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
