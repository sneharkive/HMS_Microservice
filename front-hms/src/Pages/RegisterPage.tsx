import { Button, PasswordInput, SegmentedControl, TextInput } from "@mantine/core";
import { IconHeartbeat } from "@tabler/icons-react";
import { Link, useNavigate } from "react-router-dom";
import { useForm } from "@mantine/form";
import { registerUser } from "../Service/UserService";
import { ErrorNotification, SuccessNotification } from "../Utility/NotificationUtil";
import { useState } from "react";

const RegisterPage = () => {  
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const form = useForm({
    initialValues: {
      role: "PATIENT",
      name: "",
      email: "",
      password: "",
      confirmPassword: ""
    },

    validate: {
      name: (value) => (value ? null : "Name is required"),
      email: (value) => (/^\S+@\S+$/.test(value) ? null : "Invalid email"),
      password: (value) =>
      !value
        ? "Password is required"
        : !/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@ $!%*?&]{8,15}$/.test(value)
        ? "Password must be 8–15 chars, include uppercase, lowercase, digit, and special char"
        : null,
      confirmPassword: (value, values) => (value === values.password ? null : "Password don't match."),
    },
  });

  const handleSubmit = (values: typeof form.values) => {
    setLoading(true);
    // console.log(values);
    registerUser(values).then((data) => {
      console.log(data);
      SuccessNotification("Registered Successfully! Please Login to continue.");
      navigate("/login");
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
            Register
          </div>
          <SegmentedControl
          color="pink.6"
            fullWidth
            size="md"
            radius="md"
            bg="none" className="[&_*]:text-white border-white border"
            data={[{label: "Patient", value: "PATIENT"}, {label: "Doctor", value: "DOCTOR"},{label: "Admin", value: "ADMIN"}]}
            {...form.getInputProps("role")}

          />
          <TextInput
            variant="unstyled"
            size="md"
            radius="md"
            placeholder="Enter your Name"
            // key={form.key("email")}
            {...form.getInputProps("name")}
          />
          <TextInput
            variant="unstyled"
            size="md"
            radius="md"
            placeholder="Enter your Email"
            // key={form.key("email")}
            {...form.getInputProps("email")}
          />
          <PasswordInput
            variant="unstyled"
            size="md"
            radius="md"
            placeholder="Enter the Password"
            // key={form.key("password")}
            {...form.getInputProps("password")}
          />
          <PasswordInput
            variant="unstyled"
            size="md"
            radius="md"
            placeholder="Confirm Password"
            // key={form.key("password")}
            {...form.getInputProps("confirmPassword")}
          />

          <Button loading={loading} type="submit" size="md" radius="md" color="pink.6">
            Register
          </Button>
          <div className="self-center text-neutral-200 text-md">
            Already have an account?{" "}
            <Link to="/login" className="hover:text-pink-600 underline">
              Login
            </Link>{" "}
          </div>
        </form>
      </div>
    </div>
  );
};

export default RegisterPage;
